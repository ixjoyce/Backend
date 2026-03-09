package org.example.withSpring;

import org.springframework.stereotype.Component;

    @Component
    public class MessageProvider {

        public void showSource(){
            System.out.println("Message generated from Spring Container");
        }
}
