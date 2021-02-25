package com.fyp.prograd.repository;

import com.fyp.prograd.model.Application;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    List<Application> findAllByPositionId(Long positionId);
    List<Application> findAllByEmail(String email);
}
