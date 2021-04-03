package com.fyp.prograd.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.ReadOnlyProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Resume {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long resumeId;
    private String name;
    @Lob
    private byte[] data;
    private Long studentId;
}
