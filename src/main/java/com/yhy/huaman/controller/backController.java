package com.yhy.huaman.controller;

import com.github.pagehelper.PageInfo;
import com.yhy.huaman.controller.ex.*;
import com.yhy.huaman.entity.Order;
import com.yhy.huaman.entity.OrderItem;
import com.yhy.huaman.entity.Product;
import com.yhy.huaman.entity.User;
import com.yhy.huaman.service.IOrderService;
import com.yhy.huaman.service.IProductService;
import com.yhy.huaman.service.IUserService;
import com.yhy.huaman.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController //其作用等同于@Controller+@ResponseBody
//@Controller
@RequestMapping("back")
public class backController extends BaseController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IOrderService orderService;
    @Autowired
    private IProductService productService;


    @GetMapping("/findallusers/{pageNum}/{pageSize}")
    public JsonResult<PageInfo<User>> quertByTitle(@PathVariable("pageNum") Integer pageNum,
                                                   @PathVariable("pageSize") Integer pageSize) {
        PageInfo<User> lists = userService.findall(pageNum, pageSize);
        return new JsonResult<>(OK, lists);
    }

    @GetMapping("/findallorders/{pageNum}/{pageSize}")
    public JsonResult<PageInfo<Order>> findallorders(@PathVariable("pageNum") Integer pageNum,
                                                     @PathVariable("pageSize") Integer pageSize) {
        PageInfo<Order> lists = orderService.findall(pageNum, pageSize);
        return new JsonResult<>(OK, lists);
    }

    @RequestMapping ("/findorderitems")
    public JsonResult<List<OrderItem>> queryOrderItemByOid(Integer oid) {
        List<OrderItem> orderItems = orderService.queryOrderItemByOid(oid);
        return new JsonResult<>(OK, orderItems);
    }

    @GetMapping("/findProductsByTitle/{pageNum}/{pageSize}/{title}")
    public JsonResult<PageInfo<Product>> findProductsByTitle(@PathVariable("pageNum") Integer pageNum,
                                                             @PathVariable("pageSize") Integer pageSize,
                                                             @PathVariable("title") String title) {
//        String titleNew = totypename(title);

        PageInfo<Product> lists = productService.queryProductByTitle(pageNum, pageSize, title);
//        System.err.println(lists.toString());
//        System.err.println(title + titleNew);
        return new JsonResult<>(OK, lists);
    }

    @RequestMapping("insertproduct")
    //@ResponseBody //表示此方法的响应结果以json格式进行数据的响应给到前端
//    请求处理方法的参数列表设置为pojo类型:
//    SpringBoot会将前端的url地址中的参数名和pojo类的属性名进行比较,如果这两个名称相同,则将值注入到pojo类中对应的属性上

//    public JsonResult<Void> insertproduct(Product product,String titlelast,String colors,String sizes) {
//        String finaltitle =totypename(product.getTitle())+"%"+titlelast;
//        product.setCategoryId( Integer.parseInt(product.getTitle()));
//        product.setTitle(finaltitle);
//        product.setItemType(colors+"%"+sizes);
////        Date date = new Date();
//        System.err.println(product.toString());
//        productService.insert(product);
//        return new JsonResult<>(OK);
//    }
    public JsonResult<Void> insertproduct(Product product,String titlelast,String colors,String sizes) {
        String finaltitle ="女大童"+"%"+titlelast;
        product.setCategoryId(0);
        product.setTitle(finaltitle);
        product.setItemType(colors+"%"+sizes);

        Date date = new Date();
        System.err.println(product.toString()+date);
        productService.insert(product);
        return new JsonResult<>(OK);
    }

    // 处理根据订单oid修改订单状态的请求
    @GetMapping("/updateStatus")
    public JsonResult<Void> updateStatusByOid(Integer oid,Integer status,HttpSession session){
        //修改订单状态
        orderService.updateOrderStatusByOid(oid,getUidFromSession(session),status);
        return new JsonResult<>(OK);
    }

    @RequestMapping("change")
    public JsonResult<Void> changeInfo(Product product,String colors,String sizes) {
        System.err.println("???");
        product.setCategoryId(0);
        product.setItemType(colors+"%"+sizes);
        productService.changeInfo(product);
        System.err.println(product);
        return new JsonResult<>(OK);
    }

    private String totypename(String title){
        String titleNew = "";
        if (title.equals("40")) {
            titleNew = titleNew+"女大童";
        } else if (title.equals("41")) {
            titleNew = titleNew+"女大童#下装#短裤";
        } else if (title.equals("42")) {
            titleNew = titleNew+"女大童#下装#长裤";
        } else if (title.equals("43")) {
            titleNew = titleNew+"女大童#上装#夹克";
        } else if (title.equals("50")) {
            titleNew = titleNew+"女中童#下装#短裤";
        } else if (title.equals("51")) {
            titleNew = titleNew+"女中童#下装#短裤";
        } else if (title.equals("52")) {
            titleNew = titleNew+"女中童#下装#长裤";
        } else if (title.equals("53")) {
            titleNew = titleNew+"女中童#上装#夹克";
        } else if (title.equals("60")) {
            titleNew = titleNew+"女小童#下装#短裤";
        } else if (title.equals("61")) {
            titleNew = titleNew+"女小童#下装#短裤";
        } else if (title.equals("62")) {
            titleNew = titleNew+"女小童#下装#长裤";
        } else if (title.equals("63")) {
            titleNew = titleNew+"女小童#上装#夹克";
        } else if (title.equals("70")) {
            titleNew = titleNew+"女婴童#下装#短裤";
        } else if (title.equals("71")) {
            titleNew = titleNew+"女婴童#下装#短裤";
        } else if (title.equals("72")) {
            titleNew = titleNew+"女婴童#下装#长裤";
        } else if (title.equals("73")) {
            titleNew = titleNew+"女婴童#上装#夹克";
        }else {titleNew=title;}
        return titleNew;
    }
}

