package com.yhy.huaman.service.impl;

import com.yhy.huaman.entity.Address;
import com.yhy.huaman.entity.User;
import com.yhy.huaman.mapper.AddressMapper;
import com.yhy.huaman.mapper.UserMapper;
import com.yhy.huaman.service.IAddressService;
import com.yhy.huaman.service.IDistrictService;
import com.yhy.huaman.service.ex.AddressCountLimitException;
import com.yhy.huaman.service.ex.InsertException;
import com.yhy.huaman.service.ex.UsernameNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
@Service
/**新增收货地址的实现类*/
public class AddressServiceImpl implements IAddressService {
    @Autowired
    private AddressMapper addressMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private IDistrictService districtService;


    /**
     * 为了方便日后修改最大收货地址数量,可以在配置文件
     * application.properties中定义user.address.max-count=20
     */
    //spring读取配置文件中数据:@Value("${user.address.max-count}")
    @Value("${user.address.max-count}")
    private Integer maxCount;

    @Override
    public void addNewAddress(Integer uid, String username, Address address) {
        User result = userMapper.findByUid(uid);
        if (result ==null || result.getIsDelete() == 1) {
            throw new UsernameNotFoundException("用户数据不存在");
        }

        //调用统计收货地址数量的方法
        Integer count = addressMapper.countByUid(uid);
        if (count >= maxCount) {
            throw new AddressCountLimitException("用户收货地址超出上限");
        }
/**
 * 对address对象中的数据进行补全:省市区的名字看前端代码发现前端传递过来的省市区的name分别为:
 * provinceCode,cityCode,areaCode,所以这里可以用address对象的get方法获取这三个的数据
 */
        String provinceName = districtService.getNameByCode(address.getProvinceCode());
        String cityName = districtService.getNameByCode(address.getCityCode());
        String areaName = districtService.getNameByCode(address.getAreaCode());
        address.setProvinceName(provinceName);
        address.setCityName(cityName);
        address.setAreaName(areaName);

        //uid,isDefault
        address.setUid(uid);
        Integer isDefault = count == 0 ? 1 : 0;//1表示默认收货地址,0反之
        address.setIsDefault(isDefault);

        //补全四项日志
        address.setCreatedUser(username);
        address.setModifiedUser(username);
        address.setCreatedTime(new Date());
        address.setModifiedTime(new Date());

        //调用插入收货地址的方法
        Integer rows = addressMapper.insert(address);
        if (rows != 1) {
            throw new InsertException("插入用户的收货地址时产生未知异常");
        }
    }
}

