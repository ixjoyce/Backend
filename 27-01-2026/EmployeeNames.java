import java.util.*;

public class EmployeeNames {

    public static void main(String[] args) {

        String[] employees = {
            "Ravi", "Anil", "Ravi", "Sita", "Anil", "John", "Sita"
        };

        //ARRAY → ARRAYLIST
        ArrayList<String> list = new ArrayList<>(Arrays.asList(employees));

        //REMOVE DUPLICATES 
        HashSet<String> uniqueNames = new HashSet<>(list);

        //FREQUENCY COUNT using HashMap
        HashMap<String, Integer> freqMap = new HashMap<>();

        for (String name : list) {
            freqMap.put(name, freqMap.getOrDefault(name, 0) + 1);
        }

        //USING ENHANCED FOR-LOOP (HashSet)
        System.out.println("Unique Employee Names:");
        for (String name : uniqueNames) {
            System.out.println(name);
        }

        //USING ITERATOR (ArrayList)
        System.out.println("\nAll Employees (Using Iterator):");
        Iterator<String> itr = list.iterator();
        while (itr.hasNext()) {
            System.out.println(itr.next());
        }

        //USING MAP.Entry (HashMap)
        System.out.println("\nEmployee Name Frequency:");
        for (Map.Entry<String, Integer> entry : freqMap.entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }
    }
}
