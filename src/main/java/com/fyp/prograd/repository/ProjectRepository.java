package com.fyp.prograd.repository;

import com.fyp.prograd.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
