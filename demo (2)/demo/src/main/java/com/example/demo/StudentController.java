package com.example.demo;

import com.example.demo.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private static List<Student> students = new ArrayList<>();

    static {
        students.add(new Student(1, "John", 20));
        students.add(new Student(2, "Alice", 21));
        students.add(new Student(3, "Bob", 22));
    }

    // Task 2 - GET all students
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    // Task 3 - GET student by ID
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable int id) {

        for (Student student : students) {
            if (student.getId() == id) {
                return new ResponseEntity<>(student, HttpStatus.OK);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Task 4 - Search by name
    @GetMapping("/search")
    public ResponseEntity<List<Student>> searchStudents(
            @RequestParam(defaultValue = "") String name) {

        List<Student> result = new ArrayList<>();

        for (Student s : students) {
            if (s.getName().toLowerCase().contains(name.toLowerCase())) {
                result.add(s);
            }
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // Task 5 - Add new student
    @PostMapping
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {

        students.add(student);

        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    // Task 6 - Update student
    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(
            @PathVariable int id,
            @RequestBody Student updatedStudent) {

        for (Student student : students) {

            if (student.getId() == id) {

                student.setName(updatedStudent.getName());
                student.setAge(updatedStudent.getAge());

                return new ResponseEntity<>(student, HttpStatus.OK);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Task 7 - Delete student
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable int id) {

        Iterator<Student> iterator = students.iterator();

        while (iterator.hasNext()) {

            Student student = iterator.next();

            if (student.getId() == id) {
                iterator.remove();
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}