package com.fyp.prograd.controller;

import com.fyp.prograd.model.Company;
import com.fyp.prograd.repository.CompanyRepository;
import com.fyp.prograd.service.CompanyService;
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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {

    private final CompanyService companyService;
    private final String AUTH_TOKEN = "x-api-key";

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping(value = "/add", consumes = "application/json")
    public Company add(@RequestBody Company company) {
        return companyService.add(company);
    }

    @GetMapping(value = "/findByEmail")
    public ResponseEntity<Company> findByEmail(@RequestHeader(AUTH_TOKEN) String bearerToken, @RequestParam String email) {
        return companyService.findByEmail(email);
    }

    @GetMapping(value = "/findByName")
    public ResponseEntity<Company> findByName(@RequestHeader(AUTH_TOKEN) String bearerToken, @RequestParam String name) {
        return companyService.findByName(name);
    }

    @GetMapping(value = "/findByToken")
    public ResponseEntity<Company> findByToken(@RequestHeader(AUTH_TOKEN) String bearerToken, @RequestParam String token) {
        return companyService.findByToken(token);
    }

    @GetMapping(value = "/all", produces = "application/json")
    @ResponseBody
    public List<Company> getAllCompanies(@RequestHeader(AUTH_TOKEN) String bearerToken) {
        return companyService.getAllCompanies();
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<String> delete(@RequestHeader(AUTH_TOKEN) String bearerToken, @PathVariable Long id) {
        return companyService.delete(id);
    }

    @PutMapping(value = "/update", consumes = "application/json", produces="application/json")
    @ResponseBody
    public Company update(@RequestHeader(AUTH_TOKEN) String bearerToken, @RequestBody Company company){
        return companyService.update(company);
    }

}
