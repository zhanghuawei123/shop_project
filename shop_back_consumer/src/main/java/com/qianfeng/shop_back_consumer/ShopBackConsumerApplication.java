package com.qianfeng.shop_back_consumer;

import com.github.tobato.fastdfs.FdfsClientConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication(scanBasePackages = "com.qianfeng")
@Import(value = FdfsClientConfig.class)
public class ShopBackConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopBackConsumerApplication.class, args);
    }

}

