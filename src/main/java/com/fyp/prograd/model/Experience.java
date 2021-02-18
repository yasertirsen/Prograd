package com.fyp.prograd.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Experience {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long experienceId;
    private String company;
    private String role;
    @Lob
    private String description;
    private String begin;
    private String end;
    private String location;
    private boolean voluntary;

}
