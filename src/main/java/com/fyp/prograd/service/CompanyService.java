package com.fyp.prograd.service;

import com.fyp.prograd.dto.CompanyWrapper;
import com.fyp.prograd.exceptions.ProgradException;
import com.fyp.prograd.exceptions.UserNotFoundException;
import com.fyp.prograd.model.*;
import com.fyp.prograd.modelInterface.SimpleStudent;
import com.fyp.prograd.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@Service
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final ReviewRepository reviewRepository;
    private final CompanyProfileRepository profileRepository;
    private final StudentRepository studentRepository;
    private final MailingListRepository mailingListRepository;

    @Autowired
    public CompanyService(CompanyRepository companyRepository, ReviewRepository reviewRepository,
                          CompanyProfileRepository profileRepository, StudentRepository studentRepository,
                          MailingListRepository mailingListRepository) {
        this.companyRepository = companyRepository;
        this.reviewRepository = reviewRepository;
        this.profileRepository = profileRepository;
        this.studentRepository = studentRepository;
        this.mailingListRepository = mailingListRepository;
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


    public ResponseEntity<CompanyWrapper> findByName(String name) {
        Company company = companyRepository.findByName(name).orElseThrow(()
                -> new ProgradException("Company doesn't exist"));
        List<SimpleStudent> users = new ArrayList<>();
        company.getProfile().getReviews().forEach(r -> {
            SimpleStudent student = studentRepository.findSimpleStudentByStudentId(r.getStudentId());
            users.add(student);
        });
        return new ResponseEntity<>(new CompanyWrapper(company, users)
                , HttpStatus.OK);
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

    public MailingList getMailingList(Long companyId) {
        return mailingListRepository.findByCompanyId(companyId).orElse(null);
    }

    public MailingList addToMailingList(Long companyId, String email) {
        MailingList mailingList = mailingListRepository.findByCompanyId(companyId).orElse(new MailingList());
        if(mailingList.getCompanyId() == null)
            mailingList.setCompanyId(companyId);
        if(mailingList.getEmails() == null)
            mailingList.setEmails(new ArrayList<>());
        mailingList.getEmails().add(email);
        return mailingListRepository.save(mailingList);
    }
}
