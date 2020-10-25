package com.fyp.prograd.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long positionId;
    @NotBlank(message = "Position name is required")
    private String positionName;
    @NotBlank(message = "Position description is required")
    private String positionDescription;
    @Nullable
    private double positionSalary;
    @Nullable
    private String positionUrl;
    private int clicks;
    @ManyToOne
    @JoinColumn(name = "companyId", referencedColumnName = "companyId")
    private Company company;
    @OneToMany
    private Set<Skill> requirements;
}
