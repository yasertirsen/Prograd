package com.fyp.prograd.repository;

import com.fyp.prograd.model.CompanyVerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyVerificationTokenRepository extends JpaRepository<CompanyVerificationToken, Long> {
    Optional<CompanyVerificationToken> findByToken(String token);
}
