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

import javax.servlet.http.HttpSession;

@RestController //其作用等同于@Controller+@ResponseBody
//@Controller
@RequestMapping("users")
public class UserController extends BaseController{

    @Autowired
    private IUserService userService;

    @RequestMapping("reg")
    //@ResponseBody //表示此方法的响应结果以json格式进行数据的响应给到前端
//    请求处理方法的参数列表设置为pojo类型:
//    SpringBoot会将前端的url地址中的参数名和pojo类的属性名进行比较,如果这两个名称相同,则将值注入到pojo类中对应的属性上

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


//    请求处理方法的参数列表设置为非pojo类型:
//    SpringBoot会直接将请求的参数名和方法的参数名直接进行比较,如果名称相同则自动完成值的依赖注入
//    把首次登录所获取的用户数据转移到session对象:
//    服务器本身自动创建有session对象,已经是一个全局的session对象,所以我们需要想办法获取session对象:如果直接将HttpSession类型的对象作为请求处理方法的参数,这时springboot会自动将全局的session对象注入到请求处理方法的session形参上:

    @RequestMapping("login")
    public JsonResult<User> login(String username, String password, HttpSession session) {
        User data = userService.login(username, password);

        //向session对象中完成数据的绑定(这个session是全局的,项目的任何位置都可以访问)
        session.setAttribute("uid",data.getUid());
        session.setAttribute("username",data.getUsername());

        //测试能否正常获取session中存储的数据
//        System.out.println(getUidFromSession(session));
//        System.out.println(getUsernameFromSession(session));

        return new JsonResult<User>(OK,data);
    }
    @RequestMapping("change_password")
    public JsonResult<Void> changePassword(String oldPassword,
                                           String newPassword,
                                           HttpSession session) {
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        userService.changePassword(uid,username,oldPassword,newPassword);
        JsonResult<Void> result = new JsonResult<>();
        result.setState(OK);
        result.setMessage("修改成功");
        return result;
    }

//    一打开页面就发送当前用户数据
    @RequestMapping("get_by_uid")
    public JsonResult<User> getByUid(HttpSession session) {
        User data = userService.getByUid(getUidFromSession(session));
        return new JsonResult<User>(OK,data);
    }

//    点击修改按钮更改用户数据
    @RequestMapping("change_info")
    public JsonResult<Void> changeInfo(User user,HttpSession session) {
        //user对象中有四部分的数据:username,phone,email,gender
        //控制层给业务层传递uid,并在业务层通过user.setUid(uid);将uid封装到user中
        Integer uid = getUidFromSession(session);
        userService.changeInfo(uid,user);
        return new JsonResult<>(OK);
    }




}

