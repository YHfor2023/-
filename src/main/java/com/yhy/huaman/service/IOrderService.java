package com.yhy.huaman.service;

import com.yhy.huaman.entity.Order;

public interface IOrderService {
    Order create(Integer aid, Integer[] cids, Integer uid, String username);
}
