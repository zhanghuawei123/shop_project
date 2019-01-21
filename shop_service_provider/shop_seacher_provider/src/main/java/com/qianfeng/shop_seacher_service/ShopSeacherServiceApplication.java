package com.qianfeng.shop_seacher_service;

import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.qianfeng")
@DubboComponentScan("com.qianfeng.service.impl")
public class ShopSeacherServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopSeacherServiceApplication.class, args);
    }

}

