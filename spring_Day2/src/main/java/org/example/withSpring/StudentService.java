package org.example.withSpring;

import org.springframework.stereotype.Component;

@Component
public class StudentService {

    private MessageProvider messageProvider;

    private int student1;
    private int student2;

    public StudentService(MessageProvider messageProvider){
        this.messageProvider = messageProvider;
    }

    public void setStudents(int s1, int s2){
        this.student1 = s1;
        this.student2 = s2;
    }

    public void printMessage(){

        System.out.println("Congratulations Student Roll No: " + student1);
        System.out.println("Congratulations Student Roll No: " + student2);

        messageProvider.showSource();
    }
}