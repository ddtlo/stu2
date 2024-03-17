package com.chl.mqpd;


import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MqMessageController {
    private static final Logger log = LoggerFactory.getLogger(MqMessageController.class);

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Value("${server.version}")
    private String version;

    /**
     * 普通消息
     *
     * @param id
     * @return
     */
    @RequestMapping("/n")
    public String n(@RequestParam("id") int id) {
        rocketMQTemplate.convertAndSend("topic1", "普通消息" + id);
        return "发送消息成功";
    }

    /**
     * 带tag普通消息
     *
     * @param id
     * @return
     */
    @RequestMapping("/nt")
    public String nt(@RequestParam("id") int id) {
        rocketMQTemplate.convertAndSend("topic1:" + id, "带tag普通消息" + id);
        return "发送消息成功";
    }

    /**
     * 同步版本消息
     *
     * @return
     */
    @RequestMapping("/snv")
    public String sn() {
        SendResult sendResult = rocketMQTemplate.syncSend("topic1:" + version, "同步普通消息" + version);
        return "发送消息成功";
    }
}
