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
import java.time.Instant;
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
    @NotEmpty
    private String email;
    @NotBlank
    private String password;
    @NotBlank
    private String name;
    private String companyUrl;
    private String address;
    private String recruiter;
    private String recruiterPhone;
    private Instant created;
    private boolean enabled;
    @OneToMany
    private Set<Student> hiredStudents;
}
