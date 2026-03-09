package org.example.withSpring;

import org.example.withSpring.resources.AppConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        ApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        Scanner input = new Scanner(System.in);

        System.out.println("Enter first student roll number:");
        int rollNo1 = input.nextInt();

        System.out.println("Enter second student roll number:");
        int rollNo2 = input.nextInt();

        StudentService service = context.getBean(StudentService.class);

        service.setStudents(rollNo1, rollNo2);
        service.printMessage();
    }
}