package com.yhy.huaman.mapper;


import com.yhy.huaman.entity.Address;
import com.yhy.huaman.entity.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductMapperTests {

    @Autowired
    private ProductMapper productMapper;

    @Test
    public void findNewProducts() {
        List<Product> products = productMapper.findNewList();
        System.out.println(products);
    }
    @Test
    public void searchforProducts(){
        List<Product> products=productMapper.queryProductByTitle("戴尔");
        System.out.println(products);
    }



}
