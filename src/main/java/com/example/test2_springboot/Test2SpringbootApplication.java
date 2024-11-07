package com.example.test2_springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//扫描mapper接口所在的包
@MapperScan("com.example.test2_springboot.mapper")
public class Test2SpringbootApplication {
// java -Xmx64m jar xxx.jar
    public static void main(String[] args) {
        SpringApplication.run(Test2SpringbootApplication.class, args);
    }

}
