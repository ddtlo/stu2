package com.chl.test.spring.declaration;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @Description
 * @Author 陈汉霖
 * @Date 2024/1/10
 */
@Configuration
@ComponentScan
@EnableAspectJAutoProxy
public class Test {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.chl.test.spring.declaration");
        TargetClass targetClass = context.getBean(TargetClass.class);
        targetClass.originalMethod();
        // 使用增强方法
        AdditionalInterface additional = (AdditionalInterface) targetClass;
        additional.additionalMethod();
    }
}
