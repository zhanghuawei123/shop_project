package com.qianfeng.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.qianfeng.entity.Goods;
import com.qianfeng.service.SeacherService;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@Service
public class SeacherServiceImpl implements SeacherService {
    @Autowired
    private SolrClient solrClient;
    /**
     * 通过关键词到索引库查询
     * @param keyword
     * @return
     */
    @Override
    public List<Goods> querySeacher(String keyword) {
        SolrQuery solrQuery=new SolrQuery();
        List<Goods> goodsList=null;
        if (keyword==null){
            solrQuery.setQuery("*:*");
        }else {
            solrQuery.setQuery("gtitle:"+keyword+"|| ginfo:"+keyword);
        }

        try {
            //通过索引客户端到库中查询商品
            QueryResponse query = solrClient.query(solrQuery);
            //得到搜索的结果，返回到前端展示
            SolrDocumentList results = query.getResults();
            for (SolrDocument document : results) {
                String id = (String) document.get("id");
                String gtitle = (String) document.get("gtitle");
                String ginfo = (String) document.get("ginfo");
                Integer gcount = (Integer) document.get("gcount");
                float gprice = (float)document.get("gprice");
                String gimage = (String) document.get("gimage");
                goodsList=new ArrayList<>();
                Goods goods=new Goods(
                        Integer.parseInt(id),
                        gtitle,
                        ginfo,
                        gcount,
                        0,
                        0,
                        gprice,
                        gimage
                );
                goodsList.add(goods);
            }
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return goodsList;
    }

    /**
     * 把数据插入索引库
     * @param goods
     * @return
     */
    @Override
    public int insertIndexGood(Goods goods) {
        SolrInputDocument solrInputDocument=new SolrInputDocument();
        solrInputDocument.setField("id",goods.getId());
        solrInputDocument.setField("gtitle",goods.getTitle());
        solrInputDocument.setField("ginfo",goods.getGinfo());
        solrInputDocument.setField("gprice",goods.getPrice());
        solrInputDocument.setField("gimage",goods.getGimage());
        solrInputDocument.setField("gcount",goods.getGcount());
        try {
            solrClient.add(solrInputDocument);
            solrClient.commit();
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }


}
