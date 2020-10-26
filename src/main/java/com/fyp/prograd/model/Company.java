package com.fyp.prograd.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
}
