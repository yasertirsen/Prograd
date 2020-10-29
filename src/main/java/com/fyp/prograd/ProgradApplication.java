package com.fyp.prograd;

import com.fyp.prograd.config.SwaggerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@Import(SwaggerConfig.class)
public class ProgradApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProgradApplication.class, args);
    }

}
