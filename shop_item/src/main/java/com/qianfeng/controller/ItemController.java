package com.qianfeng.controller;

import com.qianfeng.entity.Goods;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.FileWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * 生成html模板
 * @author 5.19
 * @version 1.0
 */
@Controller
@RequestMapping("/item")
public class ItemController {

    private Configuration configuration;

    @RequestMapping("/creatHtml")
    @ResponseBody
    public String creatHtml(@RequestBody Goods goods){
        Map result=new HashMap(16);
        result.put("goods",goods);

        String path=this.getClass().getResource("/static/page").getPath()+goods.getId()+".html";

        //准备freemarket模板
        try
                (Writer writer=new FileWriter(path);){
            Template template = configuration.getTemplate("goods.ftl");
            template.process(result,writer);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
