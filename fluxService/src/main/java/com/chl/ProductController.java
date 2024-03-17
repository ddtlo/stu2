package com.chl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @Description
 * @Author 陈汉霖
 * @Date 2024/1/8
 */
@RestController
public class ProductController {
    @Autowired
    private IProductService productService;

    /**
     * id 查询
     *
     * @param id
     * @return
     */
    @GetMapping("/product/{id}")
    public Mono<Product> getProductId(@PathVariable int id) {
        return productService.getProductById(id);
    }

    /**
     * @return
     */
    @GetMapping(value = "/product", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
//    @GetMapping(value = "/product")
    public Flux<Product> getProducts() {
        return productService.getAllProduct();
    }

    /**
     * @param Product
     * @return
     */
    @PostMapping("/saveProduct")
    public Mono<Void> saveProduct(@RequestBody Product Product) {
        Mono<Product> ProductMono = Mono.just(Product);
        return productService.saveProductInfo(ProductMono);
    }
}
