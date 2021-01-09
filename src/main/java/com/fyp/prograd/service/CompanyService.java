package com.fyp.prograd.service;

import com.fyp.prograd.model.Company;
import com.fyp.prograd.model.Position;
import com.fyp.prograd.repository.CompanyRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;


@Service
@Transactional
@AllArgsConstructor
public class CompanyService {

    private CompanyRepository companyRepository;

    public Company add(Company company) {
        return companyRepository.save(company);
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

    public Company update(Company company) {
        return companyRepository.save(company);
    }

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    public ResponseEntity<String> delete(Long id) {
        if(!companyRepository.existsById(id))
            return new ResponseEntity<>("Company with the id " + id + " not found!", HttpStatus.BAD_REQUEST);
        try {
            companyRepository.deleteById(id);
            return new ResponseEntity<>("Company with ID " + id + " has been deleted.", HttpStatus.OK);
        }catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
