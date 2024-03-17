package com.chl.test.es.dao;

import com.chl.test.es.index.Goods;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @Description
 * @Author 陈汉霖
 * @Date 2024/2/28
 */
@Repository
public interface GoodsDAO extends ElasticsearchRepository<Goods,Integer> {
    public Page<Goods> findByProperties(String properties, Pageable pageable);
}
