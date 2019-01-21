package com.qianfeng.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qianfeng.dao.GoodDao;
import com.qianfeng.entity.Goods;
import com.qianfeng.service.GoodService;
import com.qianfeng.service.SeacherService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 服务层，提供商品的查询，加入到数据库
 * @author
 * @data
 */
@Service
public class ServiceGoodImpl implements GoodService {
    @Autowired
    private GoodDao goodDao;
    @Reference
    private SeacherService seacherService;

    @Autowired
    private RabbitTemplate rabbitTemplate;
    /**
     * 查询所有商品
     * @return
     */
    @Override
    public List<Goods> queryGoods() {
        return goodDao.selectList(new QueryWrapper<>());
    }

    @Override
    public Goods goodAdd(Goods goods) {
        goodDao.insert(goods);

        rabbitTemplate.convertAndSend("goods_fanoutexchange",null,goods);

        //把数据插入到索引库
        seacherService.insertIndexGood(goods);
        return goods;
    }


}
