package com.chl.test.es.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description
 * @Author 陈汉霖
 * @Date 2024/2/28
 */
@Configuration
public class EsConfig {
    @Bean
    public RestHighLevelClient restHighLevelClient() {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("49.235.162.90", 51221, "http"), new HttpHost("49.235.162.90", 51222, "http")));
        return client;
    }
}
