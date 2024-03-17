//package com.chl.mql;
//
//import lombok.extern.slf4j.Slf4j;
//import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
//import org.apache.rocketmq.spring.core.RocketMQListener;
//import org.springframework.stereotype.Component;
//
///**
// * @Description
// * @Author 陈汉霖
// * @Date 2024/1/26
// */
//
//@Slf4j
//@Component
//public class mqListener {
//
//    @RocketMQMessageListener(topic = "topic1", consumerGroup = "group1")
//    public class Consumer1 implements RocketMQListener<String> {
//        @Override
//        public void onMessage(String message) {
//            log.info("消费者1:"+message);
//            System.out.println(message);
//        }
//    }
//
//    @RocketMQMessageListener(topic = "topic1", consumerGroup = "group2")
//    public class Consumer2 implements RocketMQListener<String> {
//        @Override
//        public void onMessage(String message) {
//            log.info("消费者2:"+message);
//            System.out.println(message);
//        }
//    }
//
//}
