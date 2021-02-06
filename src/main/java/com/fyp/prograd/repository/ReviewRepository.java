package com.fyp.prograd.repository;

import com.fyp.prograd.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
