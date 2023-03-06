package com.yhy.huaman.controller;

import com.yhy.huaman.entity.Address;
import com.yhy.huaman.service.IAddressService;
import com.yhy.huaman.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

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
}

