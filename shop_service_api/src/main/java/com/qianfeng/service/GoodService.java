package com.qianfeng.service;

import com.qianfeng.entity.Goods;

import java.util.List;

public interface GoodService {
    /**
     * 查询所有商品
     */
   List<Goods> queryGoods();

    /**
     * 添加一条数据进入数据库
     */
    Goods goodAdd(Goods goods);
}
