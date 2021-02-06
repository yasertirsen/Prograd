package com.fyp.prograd.service;

import com.fyp.prograd.exceptions.UserNotFoundException;
import com.fyp.prograd.model.Company;
import com.fyp.prograd.model.CompanyProfile;
import com.fyp.prograd.model.Review;
import com.fyp.prograd.repository.CompanyProfileRepository;
import com.fyp.prograd.repository.CompanyRepository;
import com.fyp.prograd.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
@Transactional
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final ReviewRepository reviewRepository;
    private final CompanyProfileRepository profileRepository;

    @Autowired
    public CompanyService(CompanyRepository companyRepository, ReviewRepository reviewRepository, CompanyProfileRepository profileRepository) {
        this.companyRepository = companyRepository;
        this.reviewRepository = reviewRepository;
        this.profileRepository = profileRepository;
    }

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

    public Review review(Review review) {
        return reviewRepository.save(review);
    }

    public List<Review> getCompanyReviews(String name) {
        return null;
    }

    public CompanyProfile updateProfile(CompanyProfile profile) throws UserNotFoundException {
        if(profileRepository.existsById(profile.getProfileId())) {
            return profileRepository.save(profile);
        }
        throw new UserNotFoundException();
    }
}
