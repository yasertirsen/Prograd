package com.fyp.prograd.repository;

import com.fyp.prograd.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    boolean existsByEmail(String email);
    boolean existsByName(String name);
    boolean existsByToken(String token);
    Optional<Company> findByName(String name);
    Company findByToken(String token);
    Company findByEmail(String email);
    Company findByCompanyId(Long id);
}
