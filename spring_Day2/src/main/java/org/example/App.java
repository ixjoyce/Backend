package org.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {

    public static void main(String[] args) {

        ApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);
//        Task 2
//        GreetingController controller =
//                context.getBean(GreetingController.class);
//
//        controller.sayHello();
        //task 3 and 4
        Engine engine = context.getBean(Engine.class);

        engine.start();

    }
}

