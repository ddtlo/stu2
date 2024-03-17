package com.chl.test.conﬁgurationProperties;

/**
 * @Description
 * @Author 陈汉霖
 * @Date 2024/2/16
 */

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@Configuration
@ConfigurationProperties(prefix = "myproperties")
public class MyProperties {
    private int size;
    private String name;
}
