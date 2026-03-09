package org.example.withoutSpring;

public class GreetingServices {
    GreetingPlace greetingPlace = new GreetingPlace();
    private int age;
    private String name;
    private String clg;
    private String relation;
    private String function;

    private String place;
    public GreetingServices(int age,String name ,String clg,String relation,String function,String place){
        this.age = age;
        this .name =name;
        this.relation=relation;
        this.clg=clg;
        this.function = function;
        this .place = place;
    }
    public void greet(){
        System.out.println("Happy " +function+" to "+name+" of age "+age+" from "+clg +" great greet from your " +relation );
        greetingPlace.sum(place);
    }
}
