package com.chl.mql;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author 陈汉霖
 * @Date 2024/1/26
 */
@Slf4j
@Component
@RocketMQMessageListener(topic = "topic1", consumerGroup = "group3")
public class mqListener2 implements RocketMQListener<String> {

    @Value("${server.version}")
    private String version;

    @Override
    public void onMessage(String message) {
        log.info("消费者3:"+message);
        System.out.println(message);
    }

}
