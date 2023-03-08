package com.yhy.huaman.controller;

import com.yhy.huaman.entity.Address;
import com.yhy.huaman.service.IAddressService;
import com.yhy.huaman.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RequestMapping("addresses")
@RestController
public class AddressController extends BaseController{
    @Autowired
    private IAddressService addressService;

    @RequestMapping("add_new_address")
    public JsonResult<Void> addNewAddress(Address address, HttpSession session) {
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        addressService.addNewAddress(uid,username,address);
        return new JsonResult<>(OK);
    }



    @RequestMapping({"","/"})
    public JsonResult<List<Address>> getByUid(HttpSession session) {
        Integer uid = getUidFromSession(session);
        List<Address> data = addressService.getByUid(uid);
        return new JsonResult<>(OK,data);
    }

    //RestFul风格的请求编写
    @RequestMapping("{aid}/set_default")
    public JsonResult<Void> setDefault(
            @PathVariable("aid") Integer aid, HttpSession session) {
        addressService.setDefault(
                aid,
                getUidFromSession(session),
                getUsernameFromSession(session));
        return new JsonResult<>(OK);
    }

    @RequestMapping("{aid}/show")
    public JsonResult<Address> show(@PathVariable("aid") Integer aid,HttpSession session) {
        Address address=  addressService.getByAid(aid,getUidFromSession(session));
        return new JsonResult<Address>(OK,address);
    }

    @RequestMapping("{aid}/update")
    public JsonResult<Void> updateInfo(@PathVariable("aid") Integer aid,Address address,HttpSession session) {
        address.setAid(aid);
        addressService.changeInfo(getUidFromSession(session),getUsernameFromSession(session),address);
        return new JsonResult<>(OK);
    }
    @RequestMapping("test")
    public JsonResult<Void> test(Integer aid,Address address){
        System.out.println("aid="+aid);
        System.out.println("address ="+address);
        return new JsonResult<>(OK);
    }

    @RequestMapping("{aid}/delete")
    public JsonResult<Void> deleteInfo(@PathVariable("aid") Integer aid) {
        addressService.deleteAddressByAid(aid);
        return new JsonResult<>(OK);
    }





}

