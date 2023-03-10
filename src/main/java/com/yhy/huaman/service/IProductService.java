package com.yhy.huaman.service;

import com.github.pagehelper.PageInfo;
import com.yhy.huaman.entity.Product;

import java.util.List;

public interface IProductService {
    /**
     * 查询热销商品的前四名
     * @return 热销商品前四名的集合
     */
    List<Product> findHotList();

    /**
     * 查询最新商品的前四名
     * @return 最新商品前四名的集合
     */
    List<Product> findNewList();

    /**
     * 根据商品id查询商品详情
     * @param id 商品id
     * @return 匹配的商品详情，如果没有匹配的数据则返回null
     */
    Product findById(Integer id);

    //根据名称进行模糊查询的抽象方法
    PageInfo<Product> queryProductByTitle(Integer pageNum, Integer pageSize, String title);


}

