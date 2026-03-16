package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class StartupRunner implements CommandLineRunner {

    @Value("${app.name}")
    private String appName;

    @Value("${app.message}")
    private String message;

    @Override
    public void run(String... args) {
        System.out.println("Application Name: " + appName);
        System.out.println("Message: " + message);
    }
}