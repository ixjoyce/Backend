package org.example;
//task 1
//import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//
//public class App {
//
//    public static void main(String[] args) {
//
//        AnnotationConfigApplicationContext context =
//                new AnnotationConfigApplicationContext(AppConfig.class);
//
//        UserService service = context.getBean(UserService.class);
//
//        service.processUser();
//
//        context.close();
//    }
//
//}


// task 2
//import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//
//public class App {
//
//    public static void main(String[] args) {
//
//        AnnotationConfigApplicationContext context =
//                new AnnotationConfigApplicationContext(AppConfig.class);
//
//        UserService service = context.getBean(UserService.class);
//
//        service.processUser();
//
//        context.close();
//    }
//
//}


//Task 3

//import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//
//public class App {
//
//    public static void main(String[] args) {
//
//        AnnotationConfigApplicationContext context =
//                new AnnotationConfigApplicationContext(AppConfig.class);
//
//        context.getBean(A.class);
//
//    }
//
//}

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        A a = context.getBean(A.class);
        B b = context.getBean(B.class);

        a.setB(b);
        b.setA(a);

        System.out.println("Circular dependency resolved using setter injection");

    }

}