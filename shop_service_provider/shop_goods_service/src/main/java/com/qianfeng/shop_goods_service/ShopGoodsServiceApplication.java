package com.qianfeng.shop_goods_service;

import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages = "com.qianfeng")
@DubboComponentScan("com.qianfeng.service.impl")
@MapperScan("com.qianfeng.dao")
@EnableTransactionManagement
public class ShopGoodsServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopGoodsServiceApplication.class, args);
    }


    @Bean
    public Queue getQueue(){
        return new Queue("goods_queue");
    }

    @Bean
    public FanoutExchange getExchange(){
        return new FanoutExchange("goods_fanoutexchange");
    }
    @Bean
    public Binding bingToBinding(Queue getQueue,FanoutExchange getExchange){
        return BindingBuilder.bind(getQueue).to(getExchange);
    }

}

