package com.fyp.prograd.repository;

import com.fyp.prograd.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, Long> {
    Optional<Image> findByName(String imageName);
    Image findByUserId(Long userId);
    Boolean existsByUserId(Long userId);
}
