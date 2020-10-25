package com.fyp.prograd.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.ManyToMany;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
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
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long studentId;
    private String firstName;
    private String surname;
    @Email
    @NotEmpty(message = "Email is required")
    private String email;
    @NotBlank(message = "Password is required")
    private String password;
    @NotBlank(message = "Username is required")
    private String username;
    private String phone;
    private String socialUrl;
    private Instant created;
    private boolean enabled;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "resumeId", referencedColumnName = "resumeId")
    private Resume resume;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "courseId", referencedColumnName = "courseId")
    private Course course;
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Skill> externalSkills;
}
