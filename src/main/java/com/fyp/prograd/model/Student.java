package com.fyp.prograd.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import javax.persistence.ManyToMany;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long studentId;
    private String firstName;
    private String surname;
    private String studentEmail;
    private String studentPassword;
    private String username;
    private String phone;
    private String socialUrl;
    @OneToOne(cascade = CascadeType.ALL)
    private Resume resume;
    @ManyToOne(cascade = CascadeType.ALL)
    private Course course;
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Skill> externalSkills;

    public Student(String firstName, String surname, String studentEmail, String username, String studentPassword, String phone, String socialUrl, Resume resume, Course course,
    Set<Skill> externalSkills) {
        this.firstName = firstName;
        this.surname = surname;
        this.studentEmail = studentEmail;
        this.username = username;
        this.studentPassword = studentPassword;
        this.phone = phone;
        this.socialUrl = socialUrl;
        this.resume = resume;
        this.course = course;
        this.externalSkills = externalSkills;
    }

    public Student() {

    }

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public String getStudentPassword() {
        return studentPassword;
    }

    public void setStudentPassword(String studentPassword) {
        this.studentPassword = studentPassword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSocialUrl() {
        return socialUrl;
    }

    public void setSocialUrl(String socialUrl) {
        this.socialUrl = socialUrl;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Resume getResume() {
        return resume;
    }

    public void setResume(Resume resume) {
        this.resume = resume;
    }

    public Set<Skill> getExternalSkills() {
        return externalSkills;
    }

    public void setExternalSkills(Set<Skill> externalSkills) {
        this.externalSkills = externalSkills;
    }
}
