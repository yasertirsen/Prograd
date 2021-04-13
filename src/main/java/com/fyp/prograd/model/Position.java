package com.fyp.prograd.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long positionId;
    private String title;
    @Lob
    private String description;
    private String location;
    private String date;
    private double salary;
    @Nullable
    private String url;
    private int clicks;
    private boolean priority;
    private boolean archive;
    @ManyToOne
    @JoinColumn(name = "companyId", referencedColumnName = "companyId")
    private Company company;
    @ManyToMany
    private Set<Skill> requirements;
}
