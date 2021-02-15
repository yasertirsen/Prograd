package com.fyp.prograd.repository;

import com.fyp.prograd.model.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResumeRepository extends JpaRepository<Resume, Long> {
    List<Resume> findAllByStudentId(Long studentId);
    Boolean existsByStudentId(Long studentId);
}
