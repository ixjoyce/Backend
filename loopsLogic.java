import java.util.*;
public class loopsLogic {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter n:");
        int n = s.nextInt();
        //print 1 to n
        for(int i=1;i<=n;i++){
            System.out.println(i);
        }


        //print even numbers 1 to n
        System.out.println("Even Numbers:");
        for(int i=1;i<=n;i++){
            if(i%2==0){
                System.out.print(i+" ");
            }
        }System.out.println();


        //sum
        int sum=0;
        for(int i=1;i<=n;i++){
            sum+=i;
        }System.out.print("Sum:"+sum);
        s.close();
    }
}
