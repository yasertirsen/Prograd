package com.fyp.prograd.controller;

import com.fyp.prograd.model.Student;
import com.fyp.prograd.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;
    private final String AUTH_TOKEN = "x-api-key";

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping(value = "/add", consumes = "application/json")
    public Student add(@RequestBody Student student) {
        return studentService.add(student);
    }

    @GetMapping(value = "/findByEmail")
    public ResponseEntity<Student> findByEmail(@RequestHeader(AUTH_TOKEN) String bearerToken, @RequestParam String email) {
        return studentService.findByEmail(email);
    }

    @GetMapping(value = "/findByUsername")
    public ResponseEntity<Student> findByUsername(@RequestHeader(AUTH_TOKEN) String bearerToken, @RequestParam String username) {
        return studentService.findByUsername(username);
    }

    @GetMapping(value = "/findByToken")
    public ResponseEntity<Student> findByToken(@RequestHeader(AUTH_TOKEN) String bearerToken, @RequestParam String token) {
        return studentService.findByToken(token);
    }

    @GetMapping(value = "/all", produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> getAllStudents(@RequestHeader(AUTH_TOKEN) String bearerToken) {
        return studentService.getAllStudents();
    }

//    @DeleteMapping(value = "/delete/{id}")
//    public ResponseEntity<?> deleteStudent(@PathVariable Long id) {
//        return studentService.deleteStudent(id);
//    }

    @PutMapping(value = "/update", consumes = "application/json", produces="application/json")
    @ResponseBody
    public Student update(@RequestHeader(AUTH_TOKEN) String bearerToken, @RequestBody Student student){
        return studentService.updateStudent(student);
    }
}
