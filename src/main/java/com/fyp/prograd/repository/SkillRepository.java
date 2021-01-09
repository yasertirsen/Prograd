package com.fyp.prograd.repository;

import com.fyp.prograd.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillRepository extends JpaRepository<Skill, Long > {
    Boolean existsBySkillName(String name);
    Skill findBySkillName(String name);
}
