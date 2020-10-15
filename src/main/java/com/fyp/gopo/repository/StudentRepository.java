package com.fyp.gopo.repository;

import com.fyp.gopo.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> { }
