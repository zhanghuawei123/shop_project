package com.qianfeng.shop_goods_service;

import com.qianfeng.entity.Goods;
import com.qianfeng.service.GoodService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShopGoodsServiceApplicationTests {
    @Autowired
    private GoodService goodService;
    @Test
    public void contextLoads() {
        List<Goods> goods = goodService.queryGoods();
        System.out.println(goods);
    }

}

