package com.yhy.huaman.mapper;


import com.yhy.huaman.entity.Address;
import com.yhy.huaman.entity.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Array;
import java.util.Arrays;
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
    public void searchforProducts() {
        List<Product> products = productMapper.queryProductByTitle("戴尔");
        System.out.println(products);
    }

    @Test
    public void getColorsAndSizes() {
        String row = "粉色#蓝色#褐色#黑色#%S%M%L%XL";
        String[] arr = row.split("#");
        String[] colors = new String[arr.length-1];
        for (int i=0 ;i<arr.length-1;i++){
            colors[i]=arr[i];
        }
        String[] arr2 = row.split("%");
        String[] sizes = new String[arr2.length-1];
        for (int i=1 ;i<arr2.length;i++){
            sizes[i-1]=arr2[i];
        }
        System.err.println(Arrays.toString(sizes));
        System.err.println(Arrays.toString(colors));

        String rownew="";
        for (int i=0 ;i<colors.length;i++){
            rownew=rownew+colors[i]+"#";
        }
        for (int i=0 ;i<sizes.length;i++){
            rownew=rownew+"%"+sizes[i];
        }
        System.err.println(rownew);
        System.err.println(row);
    }


    @Test
    public void test(){
//        String url="title=女大童#下装#短裤&pageNum=1&pageSize=12";
//        String url2=url.substring(url.indexOf("title=")+6,url.indexOf("&pageNum"));
//        System.err.println(url2);
        Product product =new Product();
        product.setId(10000031);
        product.setSafety("二级");
        System.err.println(productMapper.updateInfoByPid(product));
    }


}

