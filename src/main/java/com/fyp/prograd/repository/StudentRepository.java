package com.fyp.prograd.repository;

import com.fyp.prograd.model.Student;
import com.fyp.prograd.modelInterface.SimpleStudent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    boolean existsByToken(String token);
    Student findByUsername(String username);
    Student findByEmail(String email);
    Student findByToken(String token);
    Student findByStudentId(Long id);
    SimpleStudent findSimpleStudentByStudentId(Long id);
}
