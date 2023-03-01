package com.yhy.huaman.service.impl;

import com.yhy.huaman.entity.User;
import com.yhy.huaman.mapper.UserMapper;
import com.yhy.huaman.service.IUserService;
import com.yhy.huaman.service.ex.InsertException;
import com.yhy.huaman.service.ex.UsernameDuplicatedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.UUID;
@Service
//因为要将这个实现类交给spring管理,所以需要在类上加@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public void reg(User user) {
        //通过user参数来获取传递过来的username
        String username = user.getUsername();
        //调用mapper的findByUsername(username)判断用户是否被注册过了
        User result = userMapper.findByUsername(username);
        //判断结果集是否为null,不为null的话则需抛出用户名被占用的异常
        if (result!=null){
            throw new UsernameDuplicatedException("用户名被占用");
        }
        /**
         * 密码加密处理作用:
         * 1.后端不再能直接看到用户的密码2.忽略了密码原来的强度,提升了数据安全性
         * 密码加密处理的实现:
         * 串+password+串->交给md5算法连续加密三次
         * 串就是数据库字段中的盐值,是一个随机字符串
         */
        String oldpassword = user.getPassword();
        //1.随机生成一个盐值(大写的随机字符串)
        String salt = UUID.randomUUID().toString().toUpperCase();
        //2.将密码和盐值作为一个整体进行加密处理
        String md5Password = getMD5Password(oldpassword, salt);
        //3.将盐值保存到数据库
        user.setSalt(salt);
        //4.将加密之后的密码重新补全设置到user对象当中
        user.setPassword(md5Password);

        //补全数据:is_delete设置为0
        user.setIsDelete(0);
        //补全数据：四个日志字段信息
        user.setCreatedUser(user.getUsername());
        user.setModifiedUser(user.getUsername());
        Date date =new Date();
        user.setCreatedTime(date);
        user.setModifiedTime(date);

        //执行注册业务功能的实现
        Integer rows = userMapper.insert(user);
        if(rows!=1){
            throw new InsertException("在用户注册过程中产生了未知的异常");
        }
    }
    private String getMD5Password(String password,String salt) {
        for (int i = 0; i < 3; i++) {
            password = DigestUtils.md5DigestAsHex((salt + password + salt).getBytes()).toUpperCase();
        }
        return password;
    }

}
