package com.fyp.prograd.controller;

import com.fyp.prograd.dto.CompanyWrapper;
import com.fyp.prograd.exceptions.UserNotFoundException;
import com.fyp.prograd.model.Company;
import com.fyp.prograd.model.CompanyProfile;
import com.fyp.prograd.model.Review;
import com.fyp.prograd.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {

    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping(value = "/add", consumes = "application/json")
    public Company add(@RequestBody Company company) {
        return companyService.add(company);
    }

    @GetMapping(value = "/findByEmail")
    public ResponseEntity<Company> findByEmail(@RequestParam String email) {
        return companyService.findByEmail(email);
    }

    @GetMapping(value = "/findByName")
    public ResponseEntity<CompanyWrapper> findByName(@RequestParam String name) {
        return companyService.findByName(name);
    }

    @GetMapping(value = "/findByToken")
    public ResponseEntity<Company> findByToken(@RequestParam String token) {
        return companyService.findByToken(token);
    }

    @GetMapping(value = "/all", produces = "application/json")
    @ResponseBody
    public List<Company> getAllCompanies() {
        return companyService.getAllCompanies();
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        return companyService.delete(id);
    }

    @PutMapping(value = "/update", consumes = "application/json", produces="application/json")
    @ResponseBody
    public Company update(@RequestBody Company company){
        return companyService.update(company);
    }

    @PostMapping("/review")
    public Review review(@RequestBody Review review) {
        return companyService.review(review);
    }

    @GetMapping("/reviews")
    public List<Review> getCompanyReviews(@RequestParam String name){
        return companyService.getCompanyReviews(name);
    }

    @PutMapping("/updateProfile")
    public CompanyProfile updateProfile(@RequestBody CompanyProfile profile) throws UserNotFoundException {
        return companyService.updateProfile(profile);
    }

}
