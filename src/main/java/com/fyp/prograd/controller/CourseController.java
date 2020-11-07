package com.fyp.prograd.controller;

import com.fyp.prograd.model.Course;
import com.fyp.prograd.model.Student;
import com.fyp.prograd.repository.CourseRepository;
import com.fyp.prograd.repository.StudentRepository;
import com.fyp.prograd.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/courses")
@AllArgsConstructor
public class CourseController {

    private final CourseRepository courseRepository;
    private final StudentService studentService;

    @GetMapping(value = "/all", produces = "application/json")
    @ResponseBody
    @Transactional
    public ResponseEntity<?> getAllCourses() {

        List<Course> courses= courseRepository.findAll();
        if(courses.isEmpty())
            return new ResponseEntity<>("No Student courses were found", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    @ResponseBody
    @Transactional
    public ResponseEntity<?> getCourse(@PathVariable Long id) {
        if(courseRepository.existsById(id))
            return new ResponseEntity<>("Course was not found", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(courseRepository.findById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/add", consumes = "application/json")
    @ResponseBody
    @Transactional
    public ResponseEntity<?> addCourse(@Valid @RequestBody Course course, BindingResult result){
        if(result.hasErrors())
            return new ResponseEntity<>("Please check all course information is correct.", HttpStatus.BAD_REQUEST);
        else{
            Student student = studentService.getCurrentUser();
            //If course already exists
            if(findByCourseName(course.getCourseName(), course.getUniversity()) != null)
                course = findByCourseName(course.getCourseName(), course.getUniversity());
            else
                courseRepository.save(course);
            //Update student courseId column
            student.setCourse(course);
            studentService.updateStudent(student);
            return new ResponseEntity<>("Course assigned to student - " + course.getCourseName(), HttpStatus.OK);
        }
    }

    public Course findByCourseName(String courseName, String university) {
        return courseRepository.findByCourseNameAndUniversity(courseName, university).orElse(null);
    }

}
