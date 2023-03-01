package com.yhy.huaman;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.yhy.huaman.mapper")
//在启动类类里面指定当前项目的mapper接口在哪,然后项目启动的时候会自动加载所有的接口
public class HuaManApplication {

    public static void main(String[] args) {
        SpringApplication.run(HuaManApplication.class, args);
    }

}
