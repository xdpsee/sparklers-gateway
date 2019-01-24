package com.zhenhui.demo.sparklers.gateway.bootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.zhenhui.demo.sparklers.gateway")
public class Application {

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);

    }

}
