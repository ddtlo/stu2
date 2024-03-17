package com.chl.test.es.service;

import com.chl.test.es.dao.GoodsDAO;
import com.chl.test.es.index.Goods;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.DisMaxQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description
 * @Author 陈汉霖
 * @Date 2024/2/28
 */
@Service
public class EsGoodsServiceImpl implements EsGoodsService {
    @Autowired  //持久层对象
    private GoodsDAO goodsDAO;
    @Override
    public void insert(Goods goods) {
        Map<String, Object> pMap = new HashMap<>();
        pMap.put("spu_contents", "浙江柯桥吴桥村仓库,13398572613,周六下午");
        pMap.put("spu_measureunit", "米/码");
        pMap.put("pi_spu_category", List.of("梭织", "纯棉", "水洗布"));
        goods.setProperties(pMap.values().stream().map(Object::toString).collect(Collectors.joining(",")));
        goodsDAO.save(goods);
    }

    @Override
    public void batchInsert(List<Goods> goods) {
        Map<String, Object> pMap = new HashMap<>();
        pMap.put("spu_contents", "浙江柯桥吴桥村仓库,13398572613,周六下午");
        pMap.put("spu_measureunit", "米/码");
        pMap.put("pi_spu_category", List.of("梭织", "纯棉", "水洗布"));
        goods.forEach(g -> g.setProperties(pMap.values().stream().map(Object::toString).collect(Collectors.joining(","))));
        goodsDAO.saveAll(goods);//saveAll可以插入用集合批量插入
    }

//    @Override
//    public Page<Goods> search() {
//        // 创建请求
//        SearchSourceBuilder builder = new SearchSourceBuilder()
//                .query(QueryBuilders.termsQuery("properties", "周六"));
//
//        //搜索
//        SearchRequest searchRequest = new SearchRequest();
//        searchRequest.indices("goods");
//        searchRequest.types("_doc");
//        searchRequest.source(builder);
//        // 执行请求
//        SearchResponse response = null;
//        try {
//            response = client.search(searchRequest, RequestOptions.DEFAULT);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        // 解析查询结果
//        System.out.println(response.toString());
//        response.getHits().getHits();
//        Page<Goods> page = null;
//        return page;
//    }

    @Override
    public Page<Goods> search(String search) {
//        DisMaxQueryBuilder disMaxQueryBuilder = QueryBuilders.disMaxQuery();
//
//        BoolQueryBuilder query = QueryBuilders.boolQuery().
//                must(QueryBuilders.matchQuery("properties", "浙江").analyzer("ik_max_word"));
//        NativeSearchQuery build = new NativeSearchQueryBuilder()
//                .withQuery(query)
//                .build();

//        Goods goods = new Goods();
        Page<Goods> page = goodsDAO.findByProperties(search,Pageable.ofSize(5));
        return page;
    }
}
