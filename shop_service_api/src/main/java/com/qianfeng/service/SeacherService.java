package com.qianfeng.service;

import com.qianfeng.entity.Goods;

import java.util.List;

public interface SeacherService {

    /**
     * 通过关键字查询商品
     * @return 商品的集合
     * @param  keyword
     */
    List<Goods> querySeacher(String keyword);

    /**
     *  把添加的商品加入索引库
     * @param goods
     * @return
     */
    int insertIndexGood(Goods goods);

}
