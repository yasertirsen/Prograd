package com.fyp.prograd.repository;

import com.fyp.prograd.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
