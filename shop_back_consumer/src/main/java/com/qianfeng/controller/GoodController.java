package com.qianfeng.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.qianfeng.entity.Goods;
import com.qianfeng.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@Controller
@RequestMapping("/goods")
public class GoodController {
    @Reference
    private GoodService goodService;

    @Value("${fdfs.serverpath}")
    private String fdfspath;

    @Autowired
    private FastFileStorageClient fastFileStorageClient;

    /**
     * 查询所有商品
     * @return
     */
    @RequestMapping("/list")
    public String queryAll(Model model){
        //查询商品
        List<Goods> goods = goodService.queryGoods();
        System.out.println(goods);
        //带到页面展示
        model.addAttribute("queryList",goods);
        System.out.println(fdfspath);
        model.addAttribute("fdfspath",fdfspath);
        return "goodslist";
    }
    /**
     * 把goodsadd.html数据存储在数据库
     */
    @RequestMapping("/add")
    public String goodAdd(Goods goods){
        goodService.goodAdd(goods);
        System.out.println("-----------------------------");
        return "redirect:/goods/list";
    }

    @RequestMapping("/uploadimg")
    @ResponseBody
    public String uploadingImg(MultipartFile file) throws Exception{
        StorePath result=fastFileStorageClient.uploadImageAndCrtThumbImage(
                file.getInputStream(),
                file.getSize(),
                "png",
                null
        );
        System.out.println(result.getFullPath());
        return "{\"imgpath\":\"" + result.getFullPath() + "\"}";
    }
}
