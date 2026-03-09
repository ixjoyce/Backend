package org.example;
//task 1
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//@Configuration
//public class AppConfig {
//    @Bean
//    public UserRepository userRepository(){
//        return new UserRepository();
//    }
//
//    @Bean
//    public UserService userService(){
//        return new UserService(userRepository());
//    }
//}


//task 2
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class AppConfig {
//
//    @Bean
//    public UserRepository userRepository() {
//        return new UserRepository();
//    }
//
//    @Bean
//    public UserService userService() {
//        return new UserService(userRepository());
//    }
//
//}


//Task 3
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class AppConfig {
//
//    @Bean
//    public A a() {
//        return new A(b());
//    }
//
//    @Bean
//    public B b() {
//        return new B(a());
//    }
//
//}
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public A a() {
        return new A();
    }

    @Bean
    public B b() {
        return new B();
    }

}