package com.chl.controller;

import com.chl.service.ThreadLocalService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description
 * @Author 陈汉霖
 * @Date 2023/12/13
 */
@RestController
@RequestMapping("/hello")
public class HelloController {
    @Resource
    ThreadLocalService threadLocalService;

    @GetMapping("/world")
    private String world() {
        return "hello world twice";
    }

    @GetMapping("/tl1")
    private void tl1() {
        threadLocalService.test();
    }
}
