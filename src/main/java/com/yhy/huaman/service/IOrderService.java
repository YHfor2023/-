package com.yhy.huaman.service;

import com.github.pagehelper.PageInfo;
import com.yhy.huaman.entity.Order;
import com.yhy.huaman.entity.OrderItem;

import java.util.List;

public interface IOrderService {

    PageInfo<Order> findall(Integer pageNum, Integer pageSize);
    Order create(Integer aid, Integer[] cids, Integer uid, String username);


    //根据uid和pid删除对应的t_cart表中的数据的抽象方法
    int deleteCartByUidAndPid(Integer uid,Integer pid);

    //根据oid能从order_item表中找到对应的OrderItem信息的抽象方法
    List<OrderItem> queryOrderItemByOid(Integer oid);

    List<Order> queryOrderByUid(Integer uid);


    Order queryOrderByOid(Integer oid);

    //根据oid修改oid的订单状态的抽象方法
    int updateOrderStatusByOid(Integer oid,Integer uid,Integer status);
}
