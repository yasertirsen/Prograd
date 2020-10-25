package com.fyp.prograd.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long companyId;
    @Email
    @NotEmpty(message = "Email is required")
    private String companyEmail;
    @NotBlank(message = "Password is required")
    private String companyPassword;
    @NotBlank(message = "Company name is required")
    private String companyName;
    private String companyUrl;
    private String address;
    private String recruiter;
    private String recruiterPhone;
    @OneToMany
    private Set<Student> hiredStudents;

    public Company() {

    }


    public long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }

    public String getCompanyEmail() {
        return companyEmail;
    }

    public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail;
    }

    public String getCompanyPassword() {
        return companyPassword;
    }

    public void setCompanyPassword(String companyPassword) {
        this.companyPassword = companyPassword;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyUrl() {
        return companyUrl;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCompanyUrl(String companyUrl) {
        this.companyUrl = companyUrl;
    }

    public String getRecruiter() {
        return recruiter;
    }

    public void setRecruiter(String recruiter) {
        this.recruiter = recruiter;
    }

    public String getRecruiterPhone() {
        return recruiterPhone;
    }

    public void setRecruiterPhone(String recruiterPhone) {
        this.recruiterPhone = recruiterPhone;
    }

    public Set<Student> getHiredStudents() {
        return hiredStudents;
    }

    public void setHiredStudents(Set<Student> hiredStudents) {
        this.hiredStudents = hiredStudents;
    }
}
