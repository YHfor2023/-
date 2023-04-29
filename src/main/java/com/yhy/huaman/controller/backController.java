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
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.apache.catalina.startup.ExpandWar.deleteDir;

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
//        String category_id = totypename(title);

        PageInfo<Product> lists = productService.queryProductByTitle(pageNum, pageSize, title);
//        System.err.println(lists.toString());
//        System.err.println(title + category_id);
        return new JsonResult<>(OK, lists);
    }

    @RequestMapping("insertProduct")
    public JsonResult<Void> insertproduct(Product product,String category,String colors,String sizes) {
        String finaltitle =category.replaceAll("%23","#")+"%"+product.getTitle();
        product.setCategoryId(nameToId(category.replaceAll("%23","#")));
        product.setTitle(finaltitle);
        product.setItemType(colors+"%"+sizes);
        product.setStatus(2);

        Date date = new Date();
        System.err.println(product.toString()+date);
//        productService.insert(product);
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
//        product.setCategoryId(0);
        product.setItemType(colors+"%"+sizes);
        productService.changeInfo(product);
        System.err.println(product);
        return new JsonResult<>(OK);
    }

    /**
     * 多文件上传,返回图片路径数组
     * 建议使用这种，因为这种可以上传单张图片，也可以上传多张图片
     * 限制单张图片的在前端限制就行
     * @param image
     * @return
     */
    @RequestMapping("/uploads")
    public ArrayList upload(MultipartFile[] image,String image_attr){
        String url=System.getProperty("user.dir");//获取项目路径
        ArrayList list=new ArrayList();
//        System.err.println(image.toString());
//        System.err.println(image_attr);
        if(image.length!=0){
            String parent ="D:/FILE/Graduation_Design/HuaMan/src/main/resources/static/images/portal/"+image_attr;
            File dir = new File(parent);
            if (!dir.exists()) {//检测目录是否存在
                dir.mkdirs();//创建当前目录
            }else {
                deleteDir(dir);
                dir.mkdirs();//创建当前目录
            }
             Integer index =1;
            for(MultipartFile file:image){
                UUID id=UUID.randomUUID();
                try {

                    file.transferTo(new File(url+"\\src\\main\\resources\\static\\images\\portal\\"+image_attr+"\\"+index+".png"));
                    list.add("localhost:8080/images/portal/"+image_attr+"/"+index+".png");

                    File source = new File(url+"\\src\\main\\resources\\static\\images\\portal\\"+image_attr+"\\"+index+".png");
                    File dest = new File(url+"\\src\\main\\resources\\static\\images\\portal\\"+image_attr+"\\"+index+"_big.png");
                    try{
                        Files.copy(source.toPath(), dest.toPath());
                    } catch (IOException e){
                        e.printStackTrace();
                    }
                    list.add("localhost:8080/images/portal/"+image_attr+"/"+index+"_big.png");
                    if(index==1){
                        dest = new File(url+"\\src\\main\\resources\\static\\images\\portal\\"+image_attr+"\\"+"collect.png");
                        try{
                            Files.copy(source.toPath(), dest.toPath());
                        } catch (IOException e){
                            e.printStackTrace();
                        }
                        list.add("localhost:8080/images/portal/"+image_attr+"/"+"collect.png");
                    }
                    index = index+1;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return list;
        }
        return list;
    }

    private Integer nameToId(String title){
        Integer category_id = 0;
        if (title.equals("")) {
            category_id = 0;
        } else if (title.equals("女大童")) {
            category_id = 1;
        } else if (title.equals("女大童#上装")) {
            category_id = 2;
        } else if (title.equals("女大童#下装")) {
            category_id = 3;
        } else if (title.equals("女大童#套装")) {
            category_id = 4;
        } else if (title.equals("女大童#上装#T恤")) {
            category_id = 5;
        } else if (title.equals("女大童#上装#卫衣")) {
            category_id = 6;
        } else if (title.equals("女大童#上装#连衣裙")) {
            category_id = 7;
        } else if (title.equals("女大童#下装#短裤")) {
            category_id = 8;
        } else if (title.equals("女大童#下装#长裤")) {
            category_id = 9;
        } else if (title.equals("女大童#下装#短裙")) {
            category_id = 10;
        } else if (title.equals("女大童#套装#长袖套装")) {
            category_id = 11;
        } else if (title.equals("女大童#套装#短袖套装")) {
            category_id = 12;
        } else if (title.equals("女中童")) {
            category_id = 13;
        } else if (title.equals("女中童#上装")) {
            category_id = 14;
        } else if (title.equals("女中童#下装")) {
            category_id = 15;
        } else if (title.equals("女中童#套装")) {
            category_id = 16;
        } else if (title.equals("女中童#上装#T恤")) {
            category_id = 17;
        } else if (title.equals("女中童#上装#卫衣")) {
            category_id = 18;
        } else if (title.equals("女中童#上装#连衣裙")) {
            category_id = 19;
        } else if (title.equals("女中童#下装#短裤")) {
            category_id = 20;
        } else if (title.equals("女中童#下装#长裤")) {
            category_id = 21;
        } else if (title.equals("女中童#下装#短裙")) {
            category_id = 21;
        } else if (title.equals("女中童#套装#长袖套装")) {
            category_id = 23;
        } else if (title.equals("女中童#套装#短袖套装")) {
            category_id = 24;
        } else if (title.equals("女小童")) {
            category_id = 25;
        } else if (title.equals("女小童#上装")) {
            category_id = 26;
        } else if (title.equals("女小童#下装")) {
            category_id = 27;
        } else if (title.equals("女小童#套装")) {
            category_id = 28;
        } else if (title.equals("女小童#上装#T恤")) {
            category_id = 29;
        } else if (title.equals("女小童#上装#卫衣")) {
            category_id = 30;
        } else if (title.equals("女小童#上装#连衣裙")) {
            category_id = 31;
        } else if (title.equals("女小童#下装#短裤")) {
            category_id = 32;
        } else if (title.equals("女小童#下装#长裤")) {
            category_id = 33;
        } else if (title.equals("女小童#下装#短裙")) {
            category_id = 34;
        } else if (title.equals("女小童#套装#长袖套装")) {
            category_id = 35;
        } else if (title.equals("女小童#套装#短袖套装")) {
            category_id = 36;
        } else if (title.equals("女婴童")) {
            category_id = 37;
        } else if (title.equals("女婴童#上装")) {
            category_id = 38;
        } else if (title.equals("女婴童#下装")) {
            category_id = 39;
        } else if (title.equals("女婴童#套装")) {
            category_id = 40;
        } else if (title.equals("女婴童#上装#T恤")) {
            category_id = 41;
        } else if (title.equals("女婴童#上装#卫衣")) {
            category_id = 42;
        } else if (title.equals("女婴童#上装#连衣裙")) {
            category_id = 43;
        } else if (title.equals("女婴童#下装#短裤")) {
            category_id = 44;
        } else if (title.equals("女婴童#下装#长裤")) {
            category_id = 45;
        } else if (title.equals("女婴童#下装#短裙")) {
            category_id = 46;
        } else if (title.equals("女婴童#套装#长袖套装")) {
            category_id = 47;
        } else if (title.equals("女婴童#套装#短袖套装")) {
            category_id = 48;
        }


        return category_id;
    }
}

