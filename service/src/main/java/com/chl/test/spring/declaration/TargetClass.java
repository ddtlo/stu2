package com.chl.test.spring.declaration;

import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author 陈汉霖
 * @Date 2024/1/10
 */
@Component
public class TargetClass {
    // 原始类的方法
    public int originalMethod() {
        System.out.println("Original method in TargetClass");
        return 0;
    }
}
