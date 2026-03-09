package org.example;
// Task 2
//import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
//
//@Configuration
//@ComponentScan("org.example")
//public class AppConfig {
//}

//Task3 and 4
import org.springframework.context.annotation.Bean;
@Configuration
public class AppConfig {

    @Bean
    public Engine engine() {
        return new Engine();
    }

}


