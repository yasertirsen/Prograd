package com.fyp.prograd.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.lang.Nullable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
    @NotBlank
    private String title;
    @NotBlank
    private String description;
    private String location;
    private String date;
    @Nullable
    private double salary;
    @Nullable
    private String url;
    private int clicks;
    @ManyToOne
    @JoinColumn(name = "companyId", referencedColumnName = "companyId")
    private Company company;
    @OneToMany
    @Cascade(CascadeType.ALL)
    private Set<Skill> requirements;
}
