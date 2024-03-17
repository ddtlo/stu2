package com.chl.framework.dubbo.config;//package com.usp.framework.dubbo.config;
//
//import com.usp.framework.apisix.ApisixProperties;
//import com.usp.framework.nats.jetstream.config.NatsProperties;
//import org.springframework.boot.autoconfigure.AutoConfigureOrder;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.Ordered;
//
//
//@AutoConfigureOrder(Ordered.HIGHEST_PRECEDENCE)
//@Configuration(proxyBeanMethods = false)
//@EnableConfigurationProperties({NatsProperties.class, ApisixProperties.class})
//public class TenantConfig {
//
//    private final NatsProperties natsProperties;
//    private final ApisixProperties apisixProperties;
//
//
//    TenantConfig(NatsProperties natsProperties, ApisixProperties apisixProperties) {
//        this.natsProperties = natsProperties;
//        this.apisixProperties =apisixProperties;
//    }
//
//}
