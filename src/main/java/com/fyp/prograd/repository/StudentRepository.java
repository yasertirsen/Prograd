package com.fyp.prograd.repository;

import com.fyp.prograd.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    Optional<Student> findByUsername(String username);
    Optional<Student> findByEmail(String email);
}
