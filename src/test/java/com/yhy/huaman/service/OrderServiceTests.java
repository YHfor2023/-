package com.yhy.huaman.service;

import com.yhy.huaman.entity.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderServiceTests {
    @Autowired
    private IOrderService orderService;

    @Autowired
    IUserService userService;

    @Test
    public void create() {
        Integer[] cids = {2,4,6};
        Order order = orderService.create(13, cids, 11, "小红");
        System.out.println(order);
    }

    @Test
    public void test() {
//        System.out.println(orderService.queryOrderItemByOid(1));
//        System.out.println(orderService.deleteCartByUidAndPid(14,10000000));
        System.out.println(orderService.updateOrderStatusByOid(26,14,1));
    }
}
