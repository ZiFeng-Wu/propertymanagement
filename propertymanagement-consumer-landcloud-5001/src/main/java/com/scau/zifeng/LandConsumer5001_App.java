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
public class LandConsumer5001_App {
    public static void main(String[] args) {
        SpringApplication.run(LandConsumer5001_App.class,args);
    }
}
