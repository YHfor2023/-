package com.yhy.huaman.controller;

import com.yhy.huaman.entity.Order;
import com.yhy.huaman.entity.OrderItem;
import com.yhy.huaman.service.IOrderService;
import com.yhy.huaman.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("orders")
public class OrderController extends BaseController {
    @Autowired
    private IOrderService orderService;

    @RequestMapping("create")
    public JsonResult<Order> create(Integer aid, Integer[] cids, HttpSession session) {
        Order data = orderService.create(
                aid,
                cids,
                getUidFromSession(session),
                getUsernameFromSession(session));
        return new JsonResult<>(OK,data);
    }

    //处理根据订单oid查询order信息的请求
    @GetMapping("/queryOrder")
    public JsonResult<Order> queryOrderByOid(Integer oid){
        Order order = orderService.queryOrderByOid(oid);
        return new JsonResult<>(OK,order);
    }

    //处理根据订单oid查询orderItem信息的请求
    @GetMapping("/queryOrderItem")
    public JsonResult<List<OrderItem>> queryOrderItemByOid(Integer oid){
        List<OrderItem>  orderItems = orderService.queryOrderItemByOid(oid);
        return new JsonResult<>(OK,orderItems);
    }

    @GetMapping("/queryOrderByUid")
    public JsonResult<List<Order>> queryOrderItemByOid(HttpSession session){
        List<Order>  orders = orderService.queryOrderByUid(getUidFromSession(session));
        return new JsonResult<>(OK,orders);
    }

    // 处理根据订单oid修改订单状态的请求
    @GetMapping("/updateStatus")
    public JsonResult<Void> updateStatusByOid(Integer oid,Integer status,HttpSession session){
        //修改订单状态
        orderService.updateOrderStatusByOid(oid,getUidFromSession(session),status);
        return new JsonResult<>(OK);
    }

}
