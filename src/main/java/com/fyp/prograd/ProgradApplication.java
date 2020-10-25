package com.fyp.prograd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class ProgradApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProgradApplication.class, args);
    }

}
