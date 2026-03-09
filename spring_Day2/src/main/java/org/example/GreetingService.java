package org.example;
import org.springframework.stereotype.Service;

@Service
public class GreetingService {

    public void greet() {
        System.out.println("Hello! Welcome to Spring Core.");
    }
}