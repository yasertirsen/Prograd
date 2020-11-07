package com.fyp.prograd.service;

import com.fyp.prograd.dto.AuthenticationResponse;
import com.fyp.prograd.dto.LoginRequest;
import com.fyp.prograd.dto.RefreshTokenRequest;
import com.fyp.prograd.dto.RegisterRequest;
import com.fyp.prograd.exceptions.ProgradException;
import com.fyp.prograd.model.NotificationEmail;
import com.fyp.prograd.model.Student;
import com.fyp.prograd.model.VerificationToken;
import com.fyp.prograd.repository.StudentRepository;
import com.fyp.prograd.repository.VerificationTokenRepository;
import com.fyp.prograd.security.JwtProvider;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.http.ResponseEntity.ok;

@Service
@Transactional
public class StudentService {

    private final PasswordEncoder passwordEncoder;
    private final StudentRepository studentRepository;
    private final VerificationTokenRepository verificationTokenRepository;
    private final MailService mailService;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    private final RefreshTokenService refreshTokenService;

    @Autowired
    public StudentService(PasswordEncoder passwordEncoder, StudentRepository studentRepository, VerificationTokenRepository verificationTokenRepository, MailService mailService, AuthenticationManager authenticationManager, JwtProvider jwtProvider, RefreshTokenService refreshTokenService) {
        this.passwordEncoder = passwordEncoder;
        this.studentRepository = studentRepository;
        this.verificationTokenRepository = verificationTokenRepository;
        this.mailService = mailService;
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
        this.refreshTokenService = refreshTokenService;
    }


    public ResponseEntity<?> register(Student student) {

        if(studentRepository.existsByUsername(student.getUsername()))
            return new ResponseEntity<>(new Gson().toJson("USERNAME_EXISTS"), HttpStatus.CONFLICT);
        if(studentRepository.existsByEmail(student.getEmail()))
            return new ResponseEntity<>(new Gson().toJson("EMAIL_EXISTS"), HttpStatus.CONFLICT);

        student.setPassword(passwordEncoder.encode(student.getPassword()));
        student.setCreated(Instant.now());
        student.setEnabled(false);

        studentRepository.save(student);

        String token = generateVerificationToken(student);
        mailService.sendMail(new NotificationEmail("Account Activation - Prograd",
                student.getEmail(), "Thank you for signing up to Prograd, " +
                "please click the link below to activate your account " +
                "http://localhost:8083/verification/" + token));

        return new ResponseEntity<>(new Gson().toJson("Student registration successful"), HttpStatus.OK);
    }

    public String verifyAccount(String token) {
        Optional<VerificationToken> verificationToken = verificationTokenRepository.findByToken(token);
        verificationToken.orElseThrow(() -> new ProgradException("Invalid Token"));
        fetchUserAndEnable(verificationToken.get());

        return new Gson().toJson("Account Activated Successfully");
    }

    private String generateVerificationToken(Student student) {
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setStudent(student);

        verificationTokenRepository.save(verificationToken);
        return token;
    }

    void fetchUserAndEnable(VerificationToken verificationToken) {
        String username = verificationToken.getStudent().getUsername();
        Student student = studentRepository.findByUsername(username)
                .orElseThrow(() -> new ProgradException("User with name " + username + " was not found"));
        student.setEnabled(true);
        studentRepository.save(student);
    }

    public AuthenticationResponse login(LoginRequest loginRequest) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String token = jwtProvider.generateToken(authenticate);
        return new AuthenticationResponse(token, refreshTokenService.generateRefreshToken().getToken(),
                Instant.now().plusMillis(jwtProvider.getJwtExpirationInMillis()),
                loginRequest.getUsername());
    }

    public AuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        refreshTokenService.validateRefreshToken(refreshTokenRequest.getRefreshToken());
        String token = jwtProvider.generateTokenWithUserName(refreshTokenRequest.getUsername());
        return AuthenticationResponse.builder()
                .authenticationToken(token)
                .refreshToken(refreshTokenRequest.getRefreshToken())
                .expiresAt(Instant.now().plusMillis(jwtProvider.getJwtExpirationInMillis()))
                .username(refreshTokenRequest.getUsername())
                .build();
    }

    public ResponseEntity<String> logout(RefreshTokenRequest refreshTokenRequest) {
        refreshTokenService.deleteRefreshToken(refreshTokenRequest.getRefreshToken());
        return new ResponseEntity<>("Refresh Token Deleted Successfully", HttpStatus.OK);
    }


    public ResponseEntity<?> getAllStudents() {

        List<Student> students= studentRepository.findAll();
        if(students.isEmpty())
            return new ResponseEntity<>("No Student users were found", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(students, HttpStatus.OK);
    }


    public ResponseEntity<?> deleteStudent(@PathVariable Long id) {
        if(!studentRepository.existsById(id))
            return new ResponseEntity<>("Student with the id "+id+ " not found!", HttpStatus.BAD_REQUEST);
        try {
            studentRepository.deleteById(id);
            return new ResponseEntity<>("Student with ID " + id + " has been deleted.", HttpStatus.OK);
        }catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public Student getCurrentUser() {
        User principal = (User) SecurityContextHolder.
                getContext().getAuthentication().getPrincipal();
        return studentRepository.findByUsername(principal.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("Username not found - " + principal.getUsername()));
    }

    public ResponseEntity<?> updateStudent(Student student) {
        return new ResponseEntity<>(studentRepository.save(student), HttpStatus.OK);
    }

}
