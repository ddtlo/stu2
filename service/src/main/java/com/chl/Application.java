package com.chl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Indexed;

@SpringBootApplication
@ComponentScan({"com.chl.*"})
@EnableAspectJAutoProxy
@Indexed
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
