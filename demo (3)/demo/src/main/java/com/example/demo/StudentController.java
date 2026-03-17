package com.example.demo;

import com.example.demo.Student;
import com.example.demo.StudentRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentRepo repo;

    // Task 2 - GET all students
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = repo.findAll();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    // Task 3 - GET student by ID
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable int id) {

        return repo.findById(id)
                .map(student -> new ResponseEntity<>(student, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Task 4 - Search by name
    @GetMapping("/search")
    public ResponseEntity<List<Student>> searchStudents(
            @RequestParam(defaultValue = "") String name) {

        List<Student> result = repo.findByNameContainingIgnoreCase(name);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // Task 5 - Add new student
    @PostMapping
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {

        Student savedStudent = repo.save(student);
        return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
    }

    // Task 6 - Update student
    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(
            @PathVariable int id,
            @RequestBody Student updatedStudent) {

        return repo.findById(id)
                .map(student -> {
                    student.setName(updatedStudent.getName());
                    student.setAge(updatedStudent.getAge());
                    repo.save(student);
                    return new ResponseEntity<>(student, HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Task 7 - Delete student
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable int id) {

        if (repo.existsById(id)) {
            repo.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}