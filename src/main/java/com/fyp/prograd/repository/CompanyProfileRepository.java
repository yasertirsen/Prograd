package com.fyp.prograd.repository;

import com.fyp.prograd.model.CompanyProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyProfileRepository extends JpaRepository<CompanyProfile, Long> {
}
