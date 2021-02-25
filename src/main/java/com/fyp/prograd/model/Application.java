package com.fyp.prograd.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long applicationId;
    private String fullName;
    private String email;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "resumeId", referencedColumnName = "resumeId")
    private Resume resume;
    private Long positionId;
}
