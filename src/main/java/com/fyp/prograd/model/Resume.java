package com.fyp.prograd.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.itextpdf.text.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="resumeId")
public class Resume {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long resumeId;
//    @Lob
//    private Document resume;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "studentId", name = "studentId")
    private Student student;
}
