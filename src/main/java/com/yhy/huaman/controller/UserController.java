package com.yhy.huaman.controller;

import com.yhy.huaman.entity.User;
import com.yhy.huaman.service.IUserService;
import com.yhy.huaman.service.ex.InsertException;
import com.yhy.huaman.service.ex.UsernameDuplicatedException;
import com.yhy.huaman.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //其作用等同于@Controller+@ResponseBody
//@Controller
@RequestMapping("users")
public class UserController extends BaseController{

    @Autowired
    private IUserService userService;

    @RequestMapping("reg")
    //@ResponseBody //表示此方法的响应结果以json格式进行数据的响应给到前端
    public JsonResult<Void> reg(User user) {
        JsonResult<Void> jsonResult =new JsonResult<>();
        userService.reg(user);
        jsonResult.setMessage("注册成功");
        jsonResult.setState(OK);
        return jsonResult;

        /*//创建响应结果对象即JsonResult对象
        JsonResult<Void> result = new JsonResult<>();
        try {
            //调用userService的reg方法时可能出现异常,所以需要捕获异常
            userService.reg(user);
            result.setState(200);
            result.setMessage("用户注册成功");
        } catch (UsernameDuplicatedException e) {
            result.setState(4000);
            result.setMessage("用户名被占用");
        } catch (InsertException e) {
            result.setState(5000);
            result.setMessage("注册时产生未知的异常");
        }
        return result;*/
    }
}

