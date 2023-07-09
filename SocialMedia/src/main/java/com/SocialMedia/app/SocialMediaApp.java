package com.SocialMedia.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SocialMediaApp {
    public static void main(String[] args) {
        SpringApplication.run(SocialMediaApp.class, args);
    }
    // curl -v -X GET http://localhost:8080/users
    // curl -v -X POST http://localhost:8080/users -d '{"login": "arkadiy", "password": "123"}' -H "Content-Type: application/json"
}
