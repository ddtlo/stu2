package com.chl.test.es.dao;

import com.chl.test.es.index.Goods;
import com.chl.test.es.index.Product;
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
public interface ProductDAO extends ElasticsearchRepository<Product,Integer> {
    public Page<Product> findByName(String name, Pageable pageable);
}
