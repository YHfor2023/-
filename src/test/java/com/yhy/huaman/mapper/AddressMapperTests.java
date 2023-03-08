package com.yhy.huaman.mapper;

import com.yhy.huaman.entity.Address;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AddressMapperTests {

    @Autowired
    private AddressMapper addressMapper;

    @Test
    public void insert() {
        Address address = new Address();
        address.setUid(1);
        address.setPhone("133336");
        address.setName("女朋友");
        addressMapper.insert(address);
    }

    @Test
    public void countByUid() {
        Integer count = addressMapper.countByUid(11);
        System.out.println(count);
    }

    @Test
    public void findByUid () {
        List<Address> list = addressMapper.findByUid(14);
        System.out.println(list);
    }

    @Test
    public void updateUserAddressByAid(){
        Address address =new Address();
        address.setAid(7);
        address.setName("更新测试");
        addressMapper.updateUserAddressByAid(address);
    }

    @Test
    public void delAddressByAid(){
        addressMapper.deleteAddressByAid(8);
    }


}
