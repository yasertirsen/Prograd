package com.fyp.prograd.service;

import com.fyp.prograd.dto.AuthenticationResponse;
import com.fyp.prograd.dto.LoginRequest;
import com.fyp.prograd.dto.NotificationEmail;
import com.fyp.prograd.exceptions.ProgradException;
import com.fyp.prograd.model.Company;
import com.fyp.prograd.model.CompanyVerificationToken;
import com.fyp.prograd.model.Student;
import com.fyp.prograd.model.VerificationToken;
import com.fyp.prograd.repository.CompanyRepository;
import com.fyp.prograd.repository.CompanyVerificationTokenRepository;
import com.fyp.prograd.repository.VerificationTokenRepository;
import com.fyp.prograd.security.JwtProvider;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import static com.fyp.prograd.service.StudentService.getAuthenticationResponse;

@Service
@Transactional
@AllArgsConstructor
public class CompanyService {

    private CompanyRepository companyRepository;
    private final PasswordEncoder passwordEncoder;
    private final CompanyVerificationTokenRepository companyVerificationTokenRepository;
    private final MailService mailService;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    private final RefreshTokenService refreshTokenService;

    public ResponseEntity<?> register(Company company) {
        if(companyRepository.existsByEmail(company.getEmail()))
            return new ResponseEntity<>(new Gson().toJson("EMAIL_EXISTS"), HttpStatus.CONFLICT);
        company.setPassword(passwordEncoder.encode(company.getPassword()));
        company.setCreated(Instant.now());
        company.setEnabled(false);

        companyRepository.save(company);

        String token = generateVerificationToken(company);
        mailService.sendMail(new NotificationEmail("Account Activation - Prograd Employers",
                company.getEmail(), "Thank you for signing up to Prograd Employers, " +
                "please click the link below to activate your account " +
                "http://localhost:8082/api/companies/verification/" + token));

        return new ResponseEntity<>(new Gson().toJson("Company registration successful"), HttpStatus.OK);
    }

    public AuthenticationResponse login(Company company) {
        return getAuthenticationResponse(authenticationManager, company.getEmail(), company.getPassword(), jwtProvider, refreshTokenService);
    }

    private String generateVerificationToken(Company company) {
        String token = UUID.randomUUID().toString();
        CompanyVerificationToken companyVerificationToken = new CompanyVerificationToken();
        companyVerificationToken.setToken(token);
        companyVerificationToken.setCompany(company);

        companyVerificationTokenRepository.save(companyVerificationToken);
        return token;
    }

    public String verifyAccount(String token) {
        Optional<CompanyVerificationToken> verificationToken = companyVerificationTokenRepository.findByToken(token);
        verificationToken.orElseThrow(() -> new ProgradException("Invalid Token"));
        fetchUserAndEnable(verificationToken.get());

        return new Gson().toJson("Account Activated Successfully");
    }

    private void fetchUserAndEnable(CompanyVerificationToken verificationToken) {
        String email = verificationToken.getCompany().getEmail();
        Company company = companyRepository.findByEmail(email)
                .orElseThrow(() -> new ProgradException("Company with email: " + email + " was not found"));
        company.setEnabled(true);
        companyRepository.save(company);

    }
}
