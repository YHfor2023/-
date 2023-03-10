package com.yhy.huaman.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductServiceTests {
    @Autowired
    private IProductService productService;
    @Test
    public void search(){
        System.out.println(productService.queryProductByTitle(1,2,"戴尔"));
    }
}
