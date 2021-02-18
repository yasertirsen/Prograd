package com.fyp.prograd.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class StudentProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long profileId;
    private String bio;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "courseId", referencedColumnName = "courseId")
    private Course course;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    private Set<Skill> externalSkills;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Project> projects;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Experience> experiences;
    private String startCourse;
    private String endCourse;
    private double averageGrade;
}
