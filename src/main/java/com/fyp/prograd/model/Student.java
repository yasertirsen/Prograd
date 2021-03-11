package com.fyp.prograd.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.Instant;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long studentId;
    private String firstName;
    private String surname;
    @Email
    @NotEmpty
    private String email;
    @NotBlank
    private String password;
    @NotBlank
    private String username;
    private String phone;
    private String socialUrl;
    private Instant created;
    private Boolean enabled;
    private String role;
    private String[] authorities;
    private Boolean isLocked;
    private Long expiresIn;
    private String token;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "profileId", referencedColumnName = "profileId")
    private StudentProfile profile;


}
