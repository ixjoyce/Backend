import java.io.*;
import java.util.*;
import java.util.logging.*;

// Custom Exception 
class InvalidStudentException extends Exception {
    public InvalidStudentException(String message) {
        super(message);
    }
}

//Student Class
class Student {
    int id;
    String name;
    int age;

    Student(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    // Proper object comparison (used by HashSet)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student s = (Student) o;
        return id == s.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return id + ", " + name + ", " + age;
    }
}

//Main Class
public class StudentFileProcessor {

    private static final Logger logger =
            Logger.getLogger(StudentFileProcessor.class.getName());

    public static void main(String[] args) {

        Set<Student> studentSet = new HashSet<>();   // remove duplicates
        List<String> invalidRecords = new ArrayList<>();

        File inputFile = new File("students.txt");
        File validFile = new File("valid.txt");
        File invalidFile = new File("invalid.txt");

        logger.info("Student processing started");

        try (
            BufferedReader br = new BufferedReader(new FileReader(inputFile));
            BufferedWriter validWriter = new BufferedWriter(new FileWriter(validFile));
            BufferedWriter invalidWriter = new BufferedWriter(new FileWriter(invalidFile))
        ) {

            String line;

            while ((line = br.readLine()) != null) {

                try {
                    Student student = parseStudent(line);
                    studentSet.add(student); // duplicates auto removed

                } catch (InvalidStudentException | NumberFormatException e) {
                    invalidRecords.add(line);
                    logger.warning("Invalid record skipped: " + line);
                }
            }

            //Write valid records 
            for (Student s : studentSet) {
                validWriter.write(s.toString());
                validWriter.newLine();
            }

            //Write invalid records
            for (String invalid : invalidRecords) {
                invalidWriter.write(invalid);
                invalidWriter.newLine();
            }

            logger.info("Processing completed successfully");

        } catch (IOException e) {
            logger.severe("File error: " + e.getMessage());

        } finally {
            logger.info("Execution finished");
        }
    }

    //Parsing & Validation 
    static Student parseStudent(String line)
            throws InvalidStudentException {

        // Split using array
        String[] parts = line.split(",");

        if (parts.length != 3) {
            throw new InvalidStudentException("Incorrect format");
        }

        // Clean data using String methods
        String idStr = parts[0].trim();
        String name = parts[1].trim().replaceAll("\\s+", " ");
        String ageStr = parts[2].trim();

        // Wrapper class usage + unboxing
        Integer id = Integer.parseInt(idStr);
        Integer age = Integer.parseInt(ageStr);

        if (age < 18) {
            throw new InvalidStudentException("Age below 18");
        }

        return new Student(id, name, age);
    }
}
