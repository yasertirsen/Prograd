package com.fyp.prograd.repository;

import com.fyp.prograd.model.Position;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionRepository extends JpaRepository<Position, Long> {
    Position findByPositionId(Long id);
}
