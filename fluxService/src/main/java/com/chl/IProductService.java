package com.chl;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IProductService {
    //根据 id 查询用户
    Mono<Product> getProductById(int id);

    //查询所有用户
    Flux<Product> getAllProduct();

    //添加用户
    Mono<Void> saveProductInfo(Mono<Product> user);
}
