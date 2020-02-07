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
public class PayConsumer5101_App {
    public static void main(String[] args) {
        SpringApplication.run(PayConsumer5101_App.class,args);
    }
}
