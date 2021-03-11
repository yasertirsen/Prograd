package com.fyp.prograd.dto;

import com.fyp.prograd.model.Company;
import com.fyp.prograd.modelInterface.SimpleStudent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyWrapper {
    private Company company;
    private List<SimpleStudent> users;
}
