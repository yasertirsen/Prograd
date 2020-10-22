package com.fyp.prograd.repository;

import com.fyp.prograd.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {}
