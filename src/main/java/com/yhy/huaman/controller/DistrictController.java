package com.yhy.huaman.controller;

import com.yhy.huaman.entity.District;
import com.yhy.huaman.service.IDistrictService;
import com.yhy.huaman.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("districts")
@RestController
public class DistrictController extends BaseController{
    @Autowired
    private IDistrictService districtService;

    /**
     * 请求路径和父路径相同时用@RequestMapping({"/",""}),表
     * 示districts后面跟/或者什么也不跟都会进入这个方法
     * 点进RequestMapping发现参数类型是String[],且传入一
     * 个路径时默认有{},传入一个以上路径时需要手动添加{}
     */
    @RequestMapping({"/",""})
    public JsonResult<List<District>> getByParent(String parent) {
        List<District> data = districtService.getByParent(parent);
        return new JsonResult<>(OK,data);
    }
}
