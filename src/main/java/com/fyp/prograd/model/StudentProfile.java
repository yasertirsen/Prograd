package com.fyp.prograd.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class StudentProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long profileId;
    @OneToOne
    @JoinColumn(name = "studentId", referencedColumnName = "studentId")
    private Student student;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "resumeId", referencedColumnName = "resumeId")
    private Resume resume;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "courseId", referencedColumnName = "courseId")
    private Course course;
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Skill> externalSkills;
}
