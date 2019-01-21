package com.qianfeng.ShopItem;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = "com.qianfeng")
public class ShopItemApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShopItemApplication.class);
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
    public Binding bingToBinding(Queue getQueue, FanoutExchange getExchange){
        return BindingBuilder.bind(getQueue).to(getExchange);
    }
}
