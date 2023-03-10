package com.yhy.huaman.mapper;

import com.yhy.huaman.entity.Order;
import com.yhy.huaman.entity.OrderItem;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMapperTests {
    @Autowired
    private OrderMapper orderMapper;

    @Test
    public void insertOrder() {
        Order order = new Order();
        order.setUid(31);
        order.setRecvName("小王");
        order.setRecvPhone("133333");
        orderMapper.insertOrder(order);
    }

    @Test
    public void insertOrderItem() {
        OrderItem orderItem = new OrderItem();
        orderItem.setOid(1);
        orderItem.setPid(10000001);
        orderItem.setTitle("高档铅笔");
        orderMapper.insertOrderItem(orderItem);
    }

    @Test
    public void test() {
//        System.out.println(orderMapper.queryOrderByOid(1));
//        System.out.println(orderMapper.queryOrderItemByOid(2));
//        System.out.println(orderMapper.updateStatusByOidInt(1,1,new Date()));
//        System.out.println(orderMapper.deleteCartByUidAndPid(14,10000000));
        System.out.println(orderMapper.queryOrderByUid(14));

    }
}
