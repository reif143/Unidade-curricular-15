package com.example.casa_cultural;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
@SpringBootApplication

@EntityScan(basePackages = "com.example.casa_cultural.model")
public class CasaCulturalApplication {
    public static void main(String[] args) {
        SpringApplication.run(CasaCulturalApplication.class, args);
    }
}


