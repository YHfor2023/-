package com.yhy.huaman.controller;

import com.github.pagehelper.PageInfo;
import com.yhy.huaman.entity.Product;
import com.yhy.huaman.service.IProductService;
import com.yhy.huaman.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController extends BaseController {
    @Autowired
    private IProductService productService;

    @RequestMapping("hot_list")
    public JsonResult<List<Product>> getHotList() {
        List<Product> data = productService.findHotList();
        return new JsonResult<List<Product>>(OK, data);
    }

    @RequestMapping("new_list")
    public JsonResult<List<Product>> getNewList() {
        List<Product> data = productService.findNewList();
        return new JsonResult<List<Product>>(OK, data);
    }

    @GetMapping("{id}/details")
    public JsonResult<Product> getById(@PathVariable("id") Integer id) {
        Product data = productService.findById(id);
        return new JsonResult<Product>(OK, data);
    }

    // 处理根据产品关键字进行模糊查询的请求
    @GetMapping("/{pageNum}/{pageSize}/{title}")
    public JsonResult<PageInfo<Product>> quertByTitle(@PathVariable("pageNum") Integer pageNum,
                                                      @PathVariable("pageSize") Integer pageSize,
                                                      @PathVariable("title") String title){
//        String titleNew = totypename(title);
        PageInfo<Product> lists = productService.queryProductByTitle(pageNum, pageSize, title);
        return new JsonResult<>(OK,lists);
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
        } else if (title.equals("401")) {
            titleNew = titleNew+"女大童#上装";
        } else if (title.equals("402")) {
            titleNew = titleNew+"女大童#下装";
        } else if (title.equals("403")) {
            titleNew = titleNew+"女大童#套装";
        } else if (title.equals("41")) {
            titleNew = titleNew+"女大童#下装#短裤";
        } else if (title.equals("42")) {
            titleNew = titleNew+"女大童#下装#短裙";
        } else if (title.equals("43")) {
            titleNew = titleNew+"女大童#上装#夹克";
        } else if (title.equals("44")) {
            titleNew = titleNew+"女大童#上装#T恤";
        } else if (title.equals("45")) {
            titleNew = titleNew+"女大童#上装#卫衣";
        } else if (title.equals("46")) {
            titleNew = titleNew+"女大童#上装#连衣裙";
        } else if (title.equals("47")) {
            titleNew = titleNew+"女大童#套装#长袖套装";
        } else if (title.equals("48")) {
            titleNew = titleNew+"女大童#套装#短袖套装";
        } else if (title.equals("50")) {
            titleNew = titleNew+"女中童";
        } else if (title.equals("51")) {
            titleNew = titleNew+"女中童#下装#短裤";
        } else if (title.equals("52")) {
            titleNew = titleNew+"女中童#下装#七分裤";
        } else if (title.equals("53")) {
            titleNew = titleNew+"女中童#下装#短裙";
        } else if (title.equals("54")) {
            titleNew = titleNew+"女中童#上装#T恤";
        } else if (title.equals("55")) {
            titleNew = titleNew+"女中童#上装#卫衣";
        } else if (title.equals("56")) {
            titleNew = titleNew+"女中童#上装#针织衫";
        } else if (title.equals("57")) {
            titleNew = titleNew+"女中童#上装#连衣裙";
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

