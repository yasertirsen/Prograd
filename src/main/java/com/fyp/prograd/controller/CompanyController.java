package com.fyp.prograd.controller;

import com.fyp.prograd.model.Company;
import com.fyp.prograd.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/company")
public class CompanyController {

    private CompanyRepository companyRepository;

    @Autowired
    public CompanyController(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @GetMapping(value = "/all", produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> getAllCompanies() {
        List<Company> companies = companyRepository.findAll();
        if(companies.isEmpty())
            return new ResponseEntity<>("No Company users were found", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(companies, HttpStatus.OK);
    }

    @PostMapping(value = "/add", consumes = "application/json")
    @ResponseBody
    public ResponseEntity<?> addCompany(@Valid @RequestBody Company company, BindingResult result) {
        if(result.hasErrors())
            return new ResponseEntity<>("Please check all company information is correct.",HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(companyRepository.save(company), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/delete/{id}")
    @ResponseBody
    public ResponseEntity<?> deleteCompany(@PathVariable Long id) {
        if(!companyRepository.existsById(id))
            return new ResponseEntity<>("Company with the id " + id + " not found!", HttpStatus.BAD_REQUEST);
        try {
            companyRepository.deleteById(id);
            return new ResponseEntity<>("Company with ID " + id + " has been deleted.", HttpStatus.OK);
        }catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/update", consumes = "application/json", produces="application/json")
    @ResponseBody
    public ResponseEntity<?> updateStudent(Company company, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity<>("Some error message",HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(companyRepository.save(company), HttpStatus.OK);
    }

}
