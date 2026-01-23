import java.util.*;

public class Functions {

    //add two numbers
    public static int add(int a, int b) {
        return a + b;
    }

    //even or odd
    public static boolean isEven(int number) {
        return number % 2 == 0;
    }

    //factorial
    public static int findFactorial(int number) {
        int fact = 1;
        for (int i = 1; i <= number; i++) {
            fact = fact * i;
        }
        return fact;
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        System.out.print("Enter a number: ");
        int n = s.nextInt();

        // Even/Odd check
        if (isEven(n)) {
            System.out.println("Even Number");
        } else {
            System.out.println("Odd Number");
        }

        // Addition
        int sum = add(2, 3);
        System.out.println("Sum of a and b:");
        System.out.println(sum);

        // Factorial
        int factorial = findFactorial(n);
        System.out.println("Factorial of " + n + " is:");
        System.out.println(factorial);

        s.close();
    }
}
