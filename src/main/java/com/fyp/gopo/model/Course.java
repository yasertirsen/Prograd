package com.fyp.gopo.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long courseId;
    private String courseName;
    private String university;
    private int level;
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Module> modules;
    @OneToMany
    private Set<Industry> courseIndustries;

    public Course(String courseName, String university, int level, Set<Module> modules) {
        this.courseName = courseName;
        this.university = university;
        this.level = level;
        this.modules = modules;
    }

    public Course() {

    }

    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Set<Module> getModules() {
        return modules;
    }

    public void setModules(Set<Module> modules) {
        this.modules = modules;
    }
}
