class Student {

    private int id;
    private String name;
    private int marks;

    public Student(int id, String name, int marks) {
        this.id = id;
        this.name = name;
        this.marks = marks;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getMarks() {
        return marks;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }
    public boolean isPassed() {
        return marks >= 40;
    }
}

public class Oops {
    public static void main(String[] args) {
        Student s1 = new Student(101, "Rahul", 75);
        Student s2 = new Student(102, "Anita", 35);
        displayResult(s1);
        displayResult(s2);
    }
    public static void displayResult(Student s) {
        System.out.println("ID: " + s.getId());
        System.out.println("Name: " + s.getName());
        System.out.println("Marks: " + s.getMarks());

        if (s.isPassed()) {
            System.out.println("Result: Passed");
        } else {
            System.out.println("Result: Failed");
        }

        System.out.println("-------------------");
    }
}
