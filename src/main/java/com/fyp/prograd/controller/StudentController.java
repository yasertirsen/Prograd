package com.fyp.prograd.controller;

import com.fyp.prograd.exceptions.UserNotFoundException;
import com.fyp.prograd.model.Skill;
import com.fyp.prograd.model.Student;
import com.fyp.prograd.model.StudentProfile;
import com.fyp.prograd.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping(value = "/add", consumes = "application/json")
    public Student add(@RequestBody Student student) {
        return studentService.add(student);
    }

    @GetMapping(value = "/findByEmail")
    public ResponseEntity<Student> findByEmail(@RequestParam String email) {
        return studentService.findByEmail(email);
    }

    @GetMapping(value = "/findByUsername")
    public ResponseEntity<Student> findByUsername(@RequestParam String username) {
        return studentService.findByUsername(username);
    }

    @GetMapping(value = "/findByToken")
    public ResponseEntity<Student> findByToken(@RequestParam String token) throws UserNotFoundException {
        return studentService.findByToken(token);
    }

    @GetMapping(value = "/getById/{studentId}")
    public Student findById(@PathVariable Long studentId) {
        return studentService.findById(studentId);
    }

    @GetMapping(value = "/all", produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping(value = "/profiles", produces = "application/json")
    @ResponseBody
    public List<StudentProfile> getAllProfiles() {
        return studentService.getAllProfiles();
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long id) {
        return studentService.deleteStudent(id);
    }

    @PutMapping(value = "/update", consumes = "application/json", produces="application/json")
    @ResponseBody
    public Student update(@RequestBody Student student) throws UserNotFoundException {
        return studentService.updateStudent(student);
    }

    @PutMapping(value = "/updateProfile", consumes = "application/json", produces="application/json")
    @ResponseBody
    public StudentProfile updateProfile(@RequestBody StudentProfile profile) throws UserNotFoundException {
        return studentService.updateProfile(profile);
    }

    @PostMapping("/getSkillsNames")
    public List<String> getSkillsNames(@RequestBody StudentProfile profile) {
        return studentService.getSkillsNames(profile);
    }

    @GetMapping("/getAllSkills")
    public Set<Skill> getAllSkills() {
        return studentService.getAllSkills();
    }
}
