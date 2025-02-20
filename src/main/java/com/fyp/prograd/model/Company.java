package com.fyp.prograd.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.Instant;

@Data
@Entity
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
    private String role;
    private String[] authorities;
    private Boolean isLocked;
    private Long expiresIn;
    private String token;
    private boolean enabled;
    private boolean subscribed;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "profileId", referencedColumnName = "profileId")
    private CompanyProfile profile;
}
