package com.yhy.huaman.service;

import com.yhy.huaman.entity.User;
import com.yhy.huaman.mapper.UserMapper;
import com.yhy.huaman.service.ex.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

//@SpringBootTest表示当前的类是一个测试类,不会随同项目一块打包
@SpringBootTest
/**
 * 1.@RunWith表示启动这个单元测试类,否则这个单元测试类是不能运行的,需要传递
 * 一个参数,该参数必须是SpringRunner.class即SpringRunner的实例类型
 * 2.敲完@RunWith(SpringRunner.class)后鼠标分别放在SpringRunner和@RunWith上按alt+enter分别导入包
 * 3.单元测试类中出现的方法必须是单元测试方法
 * 4.单元测试方法的特点:必须被@Test注解修饰;返回值类型必须是void;方法的参数列表不指定任何类型;方法的访问修饰符必须是public
 */
@RunWith(SpringRunner.class)
public class UserServiceTests {
    @Autowired
    private IUserService userService;
    @Test
    public void reg() {
        /**
         * 进行插入时可能会出错抛出异常,这时需要捕获异常:
         * 1.选中    User user = new User();
         *           user.setUsername("张7");
         *           user.setPassword("123456");
         *           userService.reg(user);
         *           System.out.println("OK");
         * 2.点击导航栏的Code,然后依次点击SurroundWith->try/catch就可以捕获异常了
         * 3.Exception e没有问题,但这里我们知道是Service层的异常,所以可以改为ServiceException e
         * 4.System.out.println(e.getClass().getSimpleName());获取异常对象再获取类的名称然后输出
         * 5.System.out.println(e.getMessage());输出异常信息(是自己在ServiceException的子类类具体设置的信息)
         */
        try {
            User user = new User();
            user.setUsername("service用户注册测试reg2");
            user.setPassword("123456");
            userService.reg(user);
            System.out.println("ok");
        } catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void login() {
        //因为login方法可能抛出异常,所以应该捕获异常,但是测试时没必要写那么严谨
        User user = userService.login("页面测试user注册", "123456");
        System.out.println(user);
    }
    @Test
    public void changePassword() {
        userService.changePassword(8,"管理员","123456","321");
    }

    @Test
    public void getByUid() {
        //err是为了让输出信息为红色
        System.err.println(userService.getByUid(11).getUsername());
    }

    @Test
    public void changeInfo() {
        User user = new User();
        //这四个属性值在真实开发中都是在控制层就已经自动封装在User对象中了
        //而uid需要由控制层传给业务层并在业务层封装到user中
        user.setPhone("123456789");
        user.setEmail("123@qq.com");
        user.setUsername("mxy");
        user.setGender(0);
        userService.changeInfo(11,user);
    }

    @Test
    public void changeAvatar() {
        userService.changeAvatar(11,"222","mmm");
    }



}
