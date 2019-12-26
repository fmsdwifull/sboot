package com.liu.sboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@MapperScan(value = "com.liu.sboot.mapper")
@SpringBootApplication
public class SbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(SbootApplication.class, args);
    }

}
