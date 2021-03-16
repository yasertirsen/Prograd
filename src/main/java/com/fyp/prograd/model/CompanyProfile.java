package com.fyp.prograd.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class CompanyProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long profileId;
    @ElementCollection
    Set<Long> hiredStudents;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "profileId")
    List<Review> reviews;
}
