package com.scau.zifeng;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient //本服务启动后会自动注册进eureka服务中
public class MessageProvider6401_App {
    public static void main(String[] args) {
        SpringApplication.run(MessageProvider6401_App.class,args);
    }
}
