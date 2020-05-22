package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class SpringBootNewFeaturesApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootNewFeaturesApplication.class, args);
    }

    @RestController
    static class Hello {

        @GetMapping("/")
        public String greeting() throws InterruptedException {
            Thread.sleep(5000);
            return "Hello World";
        }
    }
}
