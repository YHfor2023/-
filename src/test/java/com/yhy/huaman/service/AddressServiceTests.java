package com.yhy.huaman.service;

import com.yhy.huaman.entity.Address;
import com.yhy.huaman.entity.User;
import com.yhy.huaman.service.ex.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

//@SpringBootTest表示当前的类是一个测试类,不会随同项目一块打包
@SpringBootTest
@RunWith(SpringRunner.class)
public class AddressServiceTests {
    @Autowired
    private IAddressService addressService;

    @Test
    public void addNewAddress() {
        Address address = new Address();
        address.setPhone("175726");
        address.setName("男朋友");
        addressService.addNewAddress(11,"mxy",address);
    }
    @Test
    public  void findAddressbyId(){
        System.out.println(addressService.getByUid(14));
    }

    @Test
    public void setDefault() {
        addressService.setDefault(1,14,"管理员");
    }

    @Test
    public void updateUserAddressByAid(){
        Address address =new Address();
        address.setAid(7);
        address.setName("更新测试2");
        addressService.changeInfo(14,"更新测试员",address);
    }

    @Test
    public void delAddressByAid(){
        addressService.deleteAddressByAid(8);
    }
}

