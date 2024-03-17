package com.chl.test.autowired.impl;

import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author 陈汉霖
 * @Date 2024/2/13
 */
@Service
public class BaseI1 implements com.chl.test.autowired.BaseI {
    @Override
    public void test() {
        System.out.println("BaseI1");
    }
}
