package com.fyp.prograd.service;

import com.fyp.prograd.model.Course;
import com.fyp.prograd.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CourseService {

    private final CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Course add(Course course) {
        if(courseRepository.existsByNameAndUniversity(course.getName(), course.getUniversity()))
            return courseRepository.findByNameAndUniversity(course.getName(), course.getUniversity());
        else
            return courseRepository.save(course);
    }

    public List<Course> getAll() {
        return courseRepository.findAll();
    }

    public ResponseEntity<Course> findById(Long id) {
        if(!courseRepository.existsById(id))
            return new ResponseEntity<>(new Course(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(courseRepository.findByCourseId(id), HttpStatus.OK);
    }

    public boolean existsByNameAndUniversity(Course course) {
        return courseRepository.existsByNameAndUniversity(course.getName(), course.getUniversity());
    }

    public Course findByNameAndUniversity(Course course) {
        return courseRepository.findByNameAndUniversity(course.getName(), course.getUniversity());
    }
}
