package com.yhy.huaman.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yhy.huaman.entity.Product;
import com.yhy.huaman.mapper.ProductMapper;
import com.yhy.huaman.service.IProductService;
import com.yhy.huaman.service.ex.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/** 处理商品数据的业务层实现类 */
@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    private ProductMapper productMapper;

    @Override
    public Integer insert(Product product){
        product.setStatus(1);
        product.setPriority(10);
        product.setCreatedUser("管理员");
        product.setCreatedTime(new Date());
        product.setModifiedUser("管理员");
        product.setModifiedTime(new Date());
        return  productMapper.insert(product);
    }

    @Override
    public List<Product> findHotList() {
        List<Product> list = productMapper.findHotList();
        for (Product product : list) {
            product.setPriority(null);
            product.setCreatedUser(null);
            product.setCreatedTime(null);
            product.setModifiedUser(null);
            product.setModifiedTime(null);
        }
        return list;
    }

    @Override
    public List<Product> findNewList() {
        List<Product> list = productMapper.findNewList();
        for (Product product : list) {
            product.setPriority(null);
            product.setCreatedUser(null);
            product.setCreatedTime(null);
            product.setModifiedUser(null);
            product.setModifiedTime(null);
        }
        return list;
    }

    @Override
    public void changeInfo(Product product) {
        product.setModifiedUser("管理员");
        product.setModifiedTime(new Date());
        productMapper.updateInfoByPid(product);
    }

    @Override
    public Product findById(Integer id) {
        Product product = productMapper.findById(id);
        // 判断查询结果是否为null
        if (product == null) {
            throw new ProductNotFoundException("尝试访问的商品数据不存在");
        }
        // 将查询结果中的部分属性设置为null
        product.setCreatedUser(null);
        product.setCreatedTime(null);
        product.setModifiedUser(null);
        product.setModifiedTime(null);

        return product;
    }

    @Override
    public PageInfo<Product> queryProductByTitle(Integer pageNum, Integer pageSize, String title) {
        //开启分页功能
        PageHelper.startPage(pageNum,pageSize);
        //调用持久层方法进行查询
        List<Product> products = productMapper.queryProductByTitle(title);
        //返回分页数据
        return new PageInfo<>(products);
    }

}
