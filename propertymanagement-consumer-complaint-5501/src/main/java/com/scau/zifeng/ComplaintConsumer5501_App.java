package com.scau.zifeng;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages= {"com.scau.zifeng"})
@ComponentScan("com.scau.zifeng")
public class ComplaintConsumer5501_App {
    public static void main(String[] args) {
        SpringApplication.run(ComplaintConsumer5501_App.class,args);
    }
}