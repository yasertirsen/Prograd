package com.fyp.prograd.repository;

import com.fyp.prograd.model.Company;
import com.fyp.prograd.model.Position;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface PositionRepository extends JpaRepository<Position, Long> {
    Position findByPositionId(Long id);
    List<Position> findAllByCompany(Company company);
}
