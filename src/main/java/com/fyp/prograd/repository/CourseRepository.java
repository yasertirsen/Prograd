package com.fyp.prograd.repository;

import com.fyp.prograd.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {
    boolean existsByNameAndUniversity(String name, String university);
    Course findByNameAndUniversity(String name, String university);
    Course findByCourseId(Long id);
}
