package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GreetingController {

    @Autowired
    private GreetingService greetingService;

    public void sayHello() {
        greetingService.greet();
    }
}