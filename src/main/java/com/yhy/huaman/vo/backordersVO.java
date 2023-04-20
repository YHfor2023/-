package com.yhy.huaman.vo;

import com.yhy.huaman.entity.OrderItem;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class backordersVO implements Serializable {
    private Integer oid;
    private Integer uid;
    private String recvName;
    private String recvPhone;
    private String recvProvince;
    private String recvCity;
    private String recvArea;
    private String recvAddress;
    private Long totalPrice;
    private Integer status;
    private Date orderTime;
    private Date payTime;
    private List<OrderItem> orderItems;
}
