package com.qianfeng.shop_seacher_service;

import com.qianfeng.entity.Goods;
import com.qianfeng.service.impl.SeacherServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShopSeacherServiceApplicationTests {
    @Autowired
    private SeacherServiceImpl seacherService;
    @Test
    public void contextLoads() {

        Goods goods=new Goods(1,"洗衣机","很好的洗衣机",199,0,0,199,"www.baidu.com");
        seacherService.insertIndexGood(goods);
    }

}

