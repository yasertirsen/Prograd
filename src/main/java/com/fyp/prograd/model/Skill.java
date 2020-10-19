package com.fyp.prograd.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import javax.persistence.Id;

@Entity
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long skillId;
    private String skillName;
    @ManyToOne(cascade = CascadeType.ALL)
    private Industry skillIndustry;

    public Skill(String skillName, Industry skillIndustry) {
        this.skillName = skillName;
        this.skillIndustry = skillIndustry;
    }

    public Skill() {

    }

    public long getSkillId() {
        return skillId;
    }

    public void setSkillId(long skillId) {
        this.skillId = skillId;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public Industry getSkillIndustry() {
        return skillIndustry;
    }

    public void setSkillIndustry(Industry skillIndustry) {
        this.skillIndustry = skillIndustry;
    }
}

