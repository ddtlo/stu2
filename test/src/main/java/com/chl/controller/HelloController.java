package com.chl.controller;

import com.chl.service.ThreadLocalService;
import com.chl.test.autowired.BaseI;
import com.chl.test.conﬁgurationProperties.MyProperties;
import com.chl.test.es.index.Goods;
import com.chl.test.es.index.Product;
import com.chl.test.es.service.EsGoodsService;
import com.chl.test.es.service.EsProductService;
import com.chl.test.resource.ResourceBaseI;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author 陈汉霖
 * @Date 2023/12/13
 */
@RestController
public class HelloController implements ApplicationContextAware {
    @Resource
    private ThreadLocalService threadLocalService;
    @Resource
    private EsGoodsService esGoodsService;
    @Resource
    private EsProductService esProductService;
    @Autowired
    @Qualifier("baseI1")
    private BaseI baseI;

    @Resource
    private ResourceBaseI resourceBaseI;


    @GetMapping("/world")
    private String world() {
        return "hello world twice";
    }

    @GetMapping("/tl1")
    private void tl1() {
        threadLocalService.test();
    }

    @RequestMapping("/autowiredt")
    private void autowiredt() {
        baseI.test();
    }

    @RequestMapping("/resourcet")
    private void resourcet() {
        resourceBaseI.test();
    }

    @RequestMapping("/properties")
    private void properties() {
        MyProperties myProperties = (MyProperties) applicationContext.getBean("myProperties");
        System.out.println(myProperties);
    }

    @RequestMapping("/esInsert")
    private void esInsert() {
//        Goods emp = new Goods(1002, Map.of("zh_CN", "浙江红色布1002"), "1000CM", "1002KG/M");
//        Goods emp = new Goods(1001, null, null, null);
//        esGoodsService.insert(emp);
        Product product = new Product(1, "红1", "1CM", "1KG/M");
        esProductService.insert(product);
    }

    @RequestMapping("/esSearch")
    private Page<Product> esSearch(String search) {
//        return esGoodsService.search(search);
        return esProductService.search(search, Pageable.ofSize(5));
    }

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
