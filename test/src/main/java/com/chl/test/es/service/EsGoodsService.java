package com.chl.test.es.service;

import com.chl.test.es.index.Goods;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EsGoodsService {
    void insert(Goods goods);

    void batchInsert(List<Goods> goods);
    Page<Goods> search(String search);
}
