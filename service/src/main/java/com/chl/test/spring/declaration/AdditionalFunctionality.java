package com.chl.test.spring.declaration;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

/**
 * @Description
 * @Author 陈汉霖
 * @Date 2024/1/10
 */
@Component
public class AdditionalFunctionality implements AdditionalInterface {
    @Override
    public int additionalMethod() {
        System.out.println("Additional functionality added!");
        Thread a = new Thread(()->{
            System.out.println("11");
        });
        a.start();
        return 1;
    }
}
