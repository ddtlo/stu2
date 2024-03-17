package com.chl.apiImpl;

import com.chl.api.HelloApi;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * @Description
 * @Author 陈汉霖
 * @Date 2023/12/18
 */
@DubboService
public class HelloApiImpl implements HelloApi {
    @Override
    public String hello() {
        return "hello";
    }
}
