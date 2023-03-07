package com.yhy.huaman.mapper;

import com.yhy.huaman.entity.Order;
import com.yhy.huaman.entity.OrderItem;

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
}
