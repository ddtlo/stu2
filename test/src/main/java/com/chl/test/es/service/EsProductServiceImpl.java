package com.chl.test.es.service;

import com.chl.test.es.dao.GoodsDAO;
import com.chl.test.es.dao.ProductDAO;
import com.chl.test.es.index.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author 陈汉霖
 * @Date 2024/3/3
 */
@Service
public class EsProductServiceImpl implements EsProductService {
    @Autowired  //持久层对象
    private ProductDAO productDAO;

    @Override
    public void insert(Product product) {
        productDAO.save(product);
    }

    @Override
    public Page<Product> search(String search, Pageable pageable) {
        return productDAO.findByName(search, pageable);
    }
}
