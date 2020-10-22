package com.fyp.prograd.controller;

import javax.validation.Valid;

import com.fyp.prograd.model.Course;
import com.fyp.prograd.model.Industry;
import com.fyp.prograd.model.Module;
import com.fyp.prograd.model.Resume;
import com.fyp.prograd.model.Skill;
import com.fyp.prograd.model.Student;
import com.fyp.prograd.repository.CourseRepository;
import com.fyp.prograd.repository.IndustryRepository;
import com.fyp.prograd.repository.ModuleRepository;
import com.fyp.prograd.repository.SkillRepository;
import com.fyp.prograd.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping(value = "/all", produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> getAllStudents() {

        List<Student> students= studentRepository.findAll();
        if(students.isEmpty())
            return new ResponseEntity<>("No Student users were found", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @PostMapping(value = "/add", consumes = "application/json")
    @ResponseBody
    public ResponseEntity<?> addStudent(@Valid @RequestBody Student student, BindingResult result){
        if(result.hasErrors())
            return new ResponseEntity<>("Please check all student information is correct.",HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(studentRepository.save(student), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long id) {
        if(!studentRepository.existsById(id))
            return new ResponseEntity<>("Student with the id "+id+ " not found!", HttpStatus.BAD_REQUEST);
        try {
            studentRepository.deleteById(id);
            return new ResponseEntity<>("Student with ID " + id + " has been deleted.", HttpStatus.OK);
        }catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/update", consumes = "application/json", produces="application/json")
    @ResponseBody
    public ResponseEntity<?> updateStudent(Student student, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity<>("Some error message",HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(studentRepository.save(student), HttpStatus.OK);
    }

    @GetMapping("/")
    public String home() {
        return "Welcome to Prograd!";
    }
}
