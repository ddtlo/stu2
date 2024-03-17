package com.chl.test.es.service;

import com.chl.test.es.index.Goods;
import com.chl.test.es.index.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EsProductService {
    void insert(Product product);

    Page<Product> search(String search, Pageable pageable);
}
