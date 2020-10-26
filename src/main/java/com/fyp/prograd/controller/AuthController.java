package com.fyp.prograd.controller;

import com.fyp.prograd.dto.AuthenticationResponse;
import com.fyp.prograd.security.JwtProvider;
import com.google.gson.Gson;
import com.fyp.prograd.dto.LoginRequest;
import com.fyp.prograd.dto.RegisterRequest;
import com.fyp.prograd.exceptions.ProgradException;
import com.fyp.prograd.model.NotificationEmail;
import com.fyp.prograd.model.Student;
import com.fyp.prograd.model.VerificationToken;
import com.fyp.prograd.repository.StudentRepository;
import com.fyp.prograd.repository.VerificationTokenRepository;
import com.fyp.prograd.service.MailService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.html.Option;
import javax.transaction.Transactional;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("api/auth")
@AllArgsConstructor
public class AuthController {


    private final PasswordEncoder passwordEncoder;
    private final StudentRepository studentRepository;
    private final VerificationTokenRepository verificationTokenRepository;
    private final MailService mailService;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;

    @PostMapping(value = "/signup", consumes = "application/json")
    @Transactional
    public ResponseEntity<?> signup(@RequestBody RegisterRequest registerRequest, BindingResult result) {
        if(result.hasErrors())
            return new ResponseEntity<>("Please check all student information is correct.", HttpStatus.BAD_REQUEST);
        else {
            Student student = new Student();
            student.setUsername(registerRequest.getUsername());
            student.setEmail(registerRequest.getEmail());
            student.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
            student.setCreated(Instant.now());
            student.setEnabled(false);

            studentRepository.save(student);

            String token = generateVerificationToken(student);
            mailService.sendMail(new NotificationEmail("Account Activation - Prograd",
                    student.getEmail(), "Thank you for signing up to Prograd, " +
                    "please click the link below to activate your account " +
                    "http://localhost:8082/api/auth/verification/" + token));

            return new ResponseEntity<>(new Gson().toJson("User registration successful"),
                    HttpStatus.OK);
        }
    }

    @GetMapping("/verification/{token}")
    public ResponseEntity<?> verifyAccount(@PathVariable String token) {
        Optional<VerificationToken> verificationToken = verificationTokenRepository.findByToken(token);
        verificationToken.orElseThrow(() -> new ProgradException("Invalid Token"));
        fetchUserAndEnable(verificationToken.get());

        return new ResponseEntity<>(new Gson().toJson("Account Activated Successfully"), HttpStatus.OK);
    }

    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody LoginRequest loginRequest) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String token = jwtProvider.generateToken(authenticate);
        return new AuthenticationResponse(token, loginRequest.getUsername());
    }

    @Transactional
    void fetchUserAndEnable(VerificationToken verificationToken) {
        String username = verificationToken.getStudent().getUsername();
        Student student = studentRepository.findByUsername(username)
                .orElseThrow(() -> new ProgradException("User with name " + username + " was not found"));
        student.setEnabled(true);
        studentRepository.save(student);
    }

    private String generateVerificationToken(Student student) {
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setStudent(student);

        verificationTokenRepository.save(verificationToken);
        return token;
    }
}
