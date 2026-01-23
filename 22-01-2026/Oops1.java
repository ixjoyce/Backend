class Employee {
    int id;
    String name;
    double monthlySalary;

    static String companyName = "Innovatechs";

    Employee(int id, String name, double monthlySalary) {
        this.id = id;
        this.name = name;
        this.monthlySalary = monthlySalary;
    }

    double calculateAnnualSalary() {
        return monthlySalary * 12;
    }

    void displayEmployeeDetails() {
        System.out.println("Company Name: " + companyName);
        System.out.println("Employee ID: " + id);
        System.out.println("Employee Name: " + name);
        System.out.println("Monthly Salary: " + monthlySalary);
        System.out.println("Annual Salary: " + calculateAnnualSalary());
        System.out.println("---------------------------");
    }
}

public class Oops1{
    public static void main(String[] args) {
        Employee e1 = new Employee(101, "Joel", 30000);
        Employee e2 = new Employee(102, "Sharon", 45000);
        e1.displayEmployeeDetails();
        e2.displayEmployeeDetails();
    }
}
