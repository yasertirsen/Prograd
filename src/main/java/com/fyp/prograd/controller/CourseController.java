package com.fyp.prograd.controller;

import com.fyp.prograd.model.Course;
import com.fyp.prograd.model.Student;
import com.fyp.prograd.repository.CourseRepository;
import com.fyp.prograd.service.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/courses")
@AllArgsConstructor
public class CourseController {

    private final CourseService courseService;
    private final String AUTH_TOKEN = "x-api-key";

    @PostMapping(value = "/add", consumes = "application/json")
    public Course add(@RequestHeader(AUTH_TOKEN) String bearerToken, @Valid @RequestBody Course course){
        return courseService.add(course);
    }

    @GetMapping(value = "/all", produces = "application/json")
    public List<Course> getAll(@RequestHeader(AUTH_TOKEN) String bearerToken) {
        return courseService.getAll();
    }

    @GetMapping(value = "/findById", produces = "application/json")
    public ResponseEntity<?> findById(@RequestHeader(AUTH_TOKEN) String bearerToken, @RequestParam Long id) {
        return courseService.findById(id);
    }

    @GetMapping(value = "/existsByNameAndUniversity", produces = "application/json")
    public boolean existsByNameAndUniversity(@RequestHeader(AUTH_TOKEN) String bearerToken, Course course) {
       return courseService.existsByNameAndUniversity(course);
    }

    @GetMapping(value = "/findByNameAndUniversity", produces = "application/json")
    public Course findByNameAndUniversity(@RequestHeader(AUTH_TOKEN) String bearerToken, Course course) {
        return courseService.findByNameAndUniversity(course);
    }
}
