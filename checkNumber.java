import java.util.*;
public class checkNumber {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter n:");
        int n= s.nextInt();
        if(n>0){
            System.out.println("Positive Number");
        }else if(n<0){
            System.out.println("Negative Number");
        }else {
            System.out.println("Zero");
        }

        if(n!=0){
            if(n%2==0){
                System.out.println("Even Number");
            }else{
                System.out.println("Odd Number");
            }
        }
        s.close();
    }
}
