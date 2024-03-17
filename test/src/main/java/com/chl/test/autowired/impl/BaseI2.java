package com.chl.test.autowired.impl;

import com.chl.test.autowired.BaseI;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author 陈汉霖
 * @Date 2024/2/13
 */
@Service
public class BaseI2 implements BaseI {
    @Override
    public void test() {
        System.out.println("BaseI2");
    }
}
