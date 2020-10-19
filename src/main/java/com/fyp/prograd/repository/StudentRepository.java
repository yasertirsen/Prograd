package com.fyp.prograd.repository;

import com.fyp.prograd.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {}
