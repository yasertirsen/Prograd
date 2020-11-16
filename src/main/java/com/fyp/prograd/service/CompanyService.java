package com.fyp.prograd.service;

import com.fyp.prograd.model.Company;
import com.fyp.prograd.repository.CompanyRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
@Transactional
@AllArgsConstructor
public class CompanyService {

    private CompanyRepository companyRepository;

    public Company register(Company company) {
        return companyRepository.save(company);
    }

    public Company login(Company company) {
        return companyRepository.findByEmail(company.getEmail());
    }

    public ResponseEntity<Company> findByEmail(String email) {
        if(companyRepository.existsByEmail(email))
            return new ResponseEntity<>(companyRepository.findByEmail(email), HttpStatus.OK);
        else
            return new ResponseEntity<>(null, HttpStatus.OK);
    }


    public ResponseEntity<Company> findByName(String name) {
        if(companyRepository.existsByName(name))
            return new ResponseEntity<>(companyRepository.findByName(name), HttpStatus.OK);
        else
            return new ResponseEntity<>(null, HttpStatus.OK);
    }

    public ResponseEntity<Company> findByToken(String token) {
        if(companyRepository.existsByToken(token))
            return new ResponseEntity<>(companyRepository.findByToken(token), HttpStatus.OK);
        else
            return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
