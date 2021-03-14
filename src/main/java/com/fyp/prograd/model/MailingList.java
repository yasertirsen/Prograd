package com.fyp.prograd.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class MailingList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mailingListId;
    private Long companyId;
    @ElementCollection
    private List<String> emails;
}
