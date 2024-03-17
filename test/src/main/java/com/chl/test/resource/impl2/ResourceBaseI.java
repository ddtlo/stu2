package com.chl.test.resource.impl2;


import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author 陈汉霖
 * @Date 2024/2/13
 */
@Service("resourceBaseI2")
public class ResourceBaseI implements com.chl.test.resource.ResourceBaseI {
    @Override
    public void test() {
        System.out.println("ResourceBaseImpl2");
    }
}
