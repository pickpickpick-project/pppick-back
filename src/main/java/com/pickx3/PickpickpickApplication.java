package com.pickx3;

import com.pickx3.config.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class PickpickpickApplication {
    public static void main(String[] args) {
        SpringApplication.run(PickpickpickApplication.class, args);
    }

    @GetMapping("/test")
    public String test() {
        return "test";
    }

}
