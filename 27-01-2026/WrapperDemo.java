import java.util.*;

public class WrapperDemo {

    public static void main(String[] args) {

        // Input as Strings
        String[] inputs = {"10", "20.5", "abc", "30", "15.5", "xyz", "40"};

        // List to store Number objects
        List<Number> numberList = new ArrayList<>();

        System.out.println("Processing Inputs...\n");

        //Convert Strings to Wrapper Objects
        for (String input : inputs) {
            try {
                if (input.contains(".")) {
                    // Autoboxing Double
                    Double d = Double.parseDouble(input);
                    numberList.add(d);
                } else {
                    // Autoboxing Integer
                    Integer i = Integer.parseInt(input);
                    numberList.add(i);
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input logged: " + input);
            }
        }

        //Calculations
        int intSum = 0;
        double doubleSum = 0.0;
        int doubleCount = 0;

        // for-each loop (Unboxing happens here)
        for (Number num : numberList) {
            if (num instanceof Integer) {
                intSum += num.intValue();   // Unboxing
            } else if (num instanceof Double) {
                doubleSum += num.doubleValue(); // Unboxing
                doubleCount++;
            }
        }

        //Average of floating-point numbers
        double average = (doubleCount > 0) ? doubleSum / doubleCount : 0;

        //Iterator demonstration
        System.out.println("\nStored Numbers:");
        Iterator<Number> itr = numberList.iterator();
        while (itr.hasNext()) {
            System.out.println(itr.next());
        }

        //Output Results
        System.out.println("\nSum of Integers: " + intSum);
        System.out.println("Average of Floating Numbers: " + average);
    }
}
