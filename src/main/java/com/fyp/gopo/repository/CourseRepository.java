package com.fyp.gopo.repository;

import com.fyp.gopo.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
