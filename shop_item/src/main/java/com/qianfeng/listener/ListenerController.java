package com.qianfeng.listener;

import com.qianfeng.entity.Goods;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

@Component
@RabbitListener(queues = "goods_queue")
public class ListenerController {
    @Autowired
    private Configuration configuration;

    @RabbitHandler
    public void handleMessage(Goods goods){
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
    }

}
