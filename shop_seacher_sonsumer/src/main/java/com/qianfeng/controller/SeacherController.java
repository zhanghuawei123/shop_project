package com.qianfeng.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qianfeng.entity.Goods;
import com.qianfeng.service.SeacherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/seacher")
public class SeacherController {
    @Reference
    private SeacherService seacherService;


    @RequestMapping("/query")
    public String queryByIndex(String keyword, Model model){
        List<Goods> goodsList = seacherService.querySeacher(keyword);
        System.out.println(goodsList);
        model.addAttribute("goodsList",goodsList);
        return "seacherList";
    }
}
