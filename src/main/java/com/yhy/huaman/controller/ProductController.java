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
        PageInfo<Product> lists = productService.queryProductByTitle(pageNum, pageSize, title);
        return new JsonResult<>(OK,lists);
    }



}

