package com.fyp.prograd.controller;

import com.fyp.prograd.exceptions.CourseNotFoundException;
import com.fyp.prograd.model.Course;
import com.fyp.prograd.service.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/courses")
@AllArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @PostMapping(value = "/add", consumes = "application/json")
    public Course add(@Valid @RequestBody Course course){
        return courseService.add(course);
    }

    @GetMapping(value = "/all", produces = "application/json")
    public List<Course> getAll() {
        return courseService.getAll();
    }

    @GetMapping(value = "/findById", produces = "application/json")
    public Course findById(@RequestParam Long id) throws CourseNotFoundException {
        return courseService.findById(id);
    }

    @GetMapping(value = "/existsByNameAndUniversity", produces = "application/json")
    public boolean existsByNameAndUniversity(Course course) {
       return courseService.existsByNameAndUniversity(course);
    }

    @GetMapping(value = "/findByNameAndUniversity", produces = "application/json")
    public Course findByNameAndUniversity(Course course) {
        return courseService.findByNameAndUniversity(course);
    }

    @PutMapping("/update")
    @ResponseBody
    public Course updateCourse(@RequestBody Course course) throws CourseNotFoundException {
        return courseService.update(course);
    }
}
