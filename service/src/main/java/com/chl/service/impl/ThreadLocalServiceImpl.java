package com.chl.service.impl;

import com.chl.service.ThreadLocalService;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author 陈汉霖
 * @Date 2024/2/10
 */
@Service
public class ThreadLocalServiceImpl implements ThreadLocalService {
    ThreadLocal<String> threadLocalString1 = new ThreadLocal<>();
    @Override
    public void test() {
        threadLocalString1.remove();
        threadLocalString1.set("tl1");
        threadLocalString1.remove();
    }
}
