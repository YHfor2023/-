package com.yhy.huaman.mapper;

import com.yhy.huaman.entity.Order;
import com.yhy.huaman.entity.OrderItem;

import java.util.Date;
import java.util.List;

public interface OrderMapper {
    /**
     * 插入订单数据
     * @param order 订单数据
     * @return 受影响的行数
     */
    Integer insertOrder(Order order);

    /**
     * 插入某一个订单中商品数据
     * @param orderItem 订单中商品数据
     * @return 受影响的行数
     */
    Integer insertOrderItem(OrderItem orderItem);

    /**
     * 根据订单号查询订单
     * @param oid 订单号
     * @return 订单信息
     */
    Order queryOrderByOid(Integer oid);

    /**
     * 根据订单号修改支付状态和支付时间
     * @param oid 订单号
     * @param status 订单状态
     * @param payTime 支付时间
     * @return 影响行数
     */
    int updateStatusByOidInt(Integer oid, Integer status, Date payTime);

    /**
     * //根据oid能从order_item表中找到对应的pid信息
     * @param oid 订单号
     * @return 订单商品 列表
     */
    List<OrderItem> queryOrderItemByOid(Integer oid);

    /**
     * //根据uid能从order_item表中找到对应的pid信息
     * @param uid 用户id
     * @return 订单 列表
     */
    List<Order> queryOrderByUid(Integer uid);

    /**
     * //根据uid和pid删除对应的t_cart表中的数据
     * @param uid 用户id
     * @param pid 商品id
     * @return 影响行数
     */
    int deleteCartByUidAndPid(Integer uid,Integer pid);




}
