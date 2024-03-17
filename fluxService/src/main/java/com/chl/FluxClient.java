package com.chl;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
/**
 * @Description
 * @Author 陈汉霖
 * @Date 2024/1/8
 */
public class FluxClient {
    public static void main(String[] args) {
        //调用服务器地址
        WebClient webClient = WebClient.create("http://localhost:12954");
        //根据 id 查询
        String id = "1";
        Product productResult = webClient.get().uri("/product/{id}", id)
                .accept(MediaType.APPLICATION_JSON).retrieve().bodyToMono(Product
                        .class)
                .block();
        System.out.println("查询结果：" + productResult);
        //查询所有
        Flux<Product> results = webClient.get().uri("/product")
                .accept(MediaType.APPLICATION_JSON).retrieve().bodyToFlux(Product.class);
        results.map(product -> product)
                .buffer().doOnNext(System.out::println).blockFirst();
    }
}
