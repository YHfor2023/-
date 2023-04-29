package com.yhy.huaman.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.org.apache.xpath.internal.operations.Or;
import com.yhy.huaman.entity.Address;
import com.yhy.huaman.entity.Order;
import com.yhy.huaman.entity.OrderItem;
import com.yhy.huaman.mapper.OrderMapper;
import com.yhy.huaman.service.IAddressService;
import com.yhy.huaman.service.ICartService;
import com.yhy.huaman.service.IOrderService;
import com.yhy.huaman.service.IUserService;
import com.yhy.huaman.service.ex.DeleteException;
import com.yhy.huaman.service.ex.InsertException;
import com.yhy.huaman.service.ex.OrderNotExistsException;
import com.yhy.huaman.service.ex.UpdateException;
import com.yhy.huaman.vo.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements IOrderService {


    @Autowired
    private OrderMapper orderMapper;

    //需要调用业务层的getByAid方法
    @Autowired
    private IAddressService addressService;

    //需要调用业务层的getVOByCids方法
    @Autowired
    private ICartService cartService;

    //需要调用业务层的getByUid方法
    @Autowired
    private IUserService userService;

    @Override
    public PageInfo<Order> findall(Integer pageNum, Integer pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<Order> orders= orderMapper.findall();
        return new PageInfo<>(orders);
    }
    @Override
    public Order create(Integer aid, Integer[] cids, Integer uid, String username) {

        //返回的列表中的对象都是即将下单的
        List<CartVO> list = cartService.getVOByCids(uid, cids);

        long totalPrice = 0L;
        for (CartVO cartVO : list) {
            totalPrice += cartVO.getRealPrice()*cartVO.getNum();

        }
        Address address = addressService.getByAid(aid, uid);
        Order order = new Order();
        order.setUid(uid);

        //封装收货地址
        order.setRecvName(address.getName());
        order.setRecvPhone(address.getPhone());
        order.setRecvProvince(address.getProvinceName());
        order.setRecvCity(address.getCityName());
        order.setRecvArea(address.getAreaName());
        order.setRecvAddress(address.getAddress());

        //封装创建时间,支付状态和总价
        order.setOrderTime(new Date());
        order.setStatus(0);
        order.setTotalPrice(totalPrice);

        //封装四个日志
        order.setCreatedUser(username);
        order.setCreatedTime(new Date());
        order.setModifiedUser(username);
        order.setModifiedTime(new Date());
        Integer rows = orderMapper.insertOrder(order);
        if (rows != 1) {
            throw new InsertException("插入数据时产生未知的异常");
        }

        //插入数据——将某条订单的所有商品的详细数据插入
        for (CartVO cartVO : list) {
            OrderItem orderItem = new OrderItem();

            /**
             * 此时获取的oid不为空,因为在配置文件里面开启了oid主
             * 键自增,所以上面的代码执行插入时就自动将oid赋值了
             */
            orderItem.setOid(order.getOid());

            orderItem.setPid(cartVO.getPid());
            orderItem.setTitle(cartVO.getTitle());
            orderItem.setImage(cartVO.getImage());
            orderItem.setPrice(cartVO.getRealPrice());
            orderItem.setNum(cartVO.getNum());
            orderItem.setColor(cartVO.getColor());
            orderItem.setSize(cartVO.getSize());
            orderItem.setMadeof(cartVO.getMadeof());
            orderItem.setSafety(cartVO.getSafety());

            orderItem.setCreatedUser(username);
            orderItem.setCreatedTime(new Date());
            orderItem.setModifiedUser(username);
            orderItem.setModifiedTime(new Date());

            rows = orderMapper.insertOrderItem(orderItem);
            if (rows != 1) {
                throw new InsertException("插入数据时产生未知的异常");
            }
        }
        return order;
    }

    // 根据uid和pid删除对应的t_cart表中的数据的具体逻辑
    @Override
    public int deleteCartByUidAndPid(Integer uid, Integer pid) {
        int result =  orderMapper.deleteCartByUidAndPid(uid, pid);
        if (result == 0){
            throw new DeleteException("服务器异常，删除购物车商品失败!!");
        }
        return result;
    }

    //根据订单oid查询orderItem信息的具体逻辑
    @Override
    public List<OrderItem> queryOrderItemByOid(Integer oid) {
        List<OrderItem> orderItems = orderMapper.queryOrderItemByOid(oid);
        if (orderItems.size() == 0){
            throw new OrderNotExistsException("订单不存在！！！");
        }
        return orderItems;
    }

    @Override
    public List<Order> queryOrderByUid(Integer uid) {
        List<Order> orders = orderMapper.queryOrderByUid(uid);
        if (orders.size() == 0){
            throw new OrderNotExistsException("订单不存在！！！");
        }
        return orders;
    }

    //根据订单oid查询order信息的具体逻辑
    @Override
    public Order queryOrderByOid(Integer oid) {
        Order order = orderMapper.queryOrderByOid(oid);
        if (order == null){
            throw new OrderNotExistsException("订单不存在！！！");
        }
        return order;
    }

    //根据订单oid修改订单状态的具体逻辑
    @Override
    public int updateOrderStatusByOid(Integer oid, Integer uid, Integer status) {
        //先查询一下订单信息
        Order order = orderMapper.queryOrderByOid(oid);
        int result = 0;
        //status == 0代表刚刚创建
        if (order.getStatus() == 0){
            //修改支付时间
            Date payTime = new Date();
            result = orderMapper.updateStatusByOidInt(oid, status,payTime);

            //根据oid查找具体的OrderItem信息
            List<OrderItem> orderItems = orderMapper.queryOrderItemByOid(oid);
            for (OrderItem o: orderItems) {
                //从OrderItem中取得pid
                Integer pid = o.getPid();
                //根据pid和uid删除购物车中的商品
                orderMapper.deleteCartByUidAndPid(uid, pid);
            }
        }else {
            //除了status == 0的状况其他的都可以直接修改其状态
            //修改订单状态
            result = orderMapper.updateStatusByOidInt(oid,status,order.getPayTime());
        }
        if (result == 0){
            throw new UpdateException("服务器异常，修改订单状态失败");
        }
        return result;
    }


}
