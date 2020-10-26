package com.fyp.prograd.controller;

import com.fyp.prograd.model.Course;
import com.fyp.prograd.repository.CourseRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/course")
@AllArgsConstructor
public class CourseController {

    private final CourseRepository courseRepository;

    @GetMapping(value = "/all", produces = "application/json")
    @ResponseBody
    @Transactional
    public ResponseEntity<?> getAllCourses() {

        List<Course> courses= courseRepository.findAll();
        if(courses.isEmpty())
            return new ResponseEntity<>("No Student courses were found", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @PostMapping(value = "/add", consumes = "application/json")
    @ResponseBody
    @Transactional
    public ResponseEntity<?> addCourse(@Valid @RequestBody Course course, BindingResult result){
        if(result.hasErrors())
            return new ResponseEntity<>("Please check all course information is correct.", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(courseRepository.save(course), HttpStatus.CREATED);
    }

}
