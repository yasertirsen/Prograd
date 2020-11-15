package com.fyp.prograd.controller;

import javax.transaction.Transactional;
import javax.validation.Valid;

import com.fyp.prograd.dto.AuthenticationResponse;
import com.fyp.prograd.dto.LoginRequest;
import com.fyp.prograd.dto.RefreshTokenRequest;
import com.fyp.prograd.dto.RegisterRequest;
import com.fyp.prograd.exceptions.ProgradException;
import com.fyp.prograd.model.Student;
import com.fyp.prograd.model.VerificationToken;
import com.fyp.prograd.repository.StudentRepository;
import com.fyp.prograd.service.StudentService;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/students")
@AllArgsConstructor
public class StudentController {

    private final StudentService studentService;
    private final String AUTH_TOKEN = "x-api-key";

    @PostMapping(value = "/register", consumes = "application/json")
    public Student register(@RequestBody Student student) {
        return studentService.register(student);
    }

    @GetMapping(value = "/findByEmail")
    public ResponseEntity<Student> findByEmail(@RequestHeader(AUTH_TOKEN) String bearerToken, @RequestParam String email) {
        return studentService.findByEmail(email);
    }

    @GetMapping(value = "/findByUsername")
    public ResponseEntity<Student> findByUsername(@RequestHeader(AUTH_TOKEN) String bearerToken, @RequestParam String username) {
        return studentService.findByUsername(username);
    }

    @GetMapping(value = "/findByToken")
    public ResponseEntity<Student> findByToken(@RequestHeader(AUTH_TOKEN) String bearerToken, @RequestParam String token) {
        return studentService.findByToken(token);
    }

    @PostMapping("/login")
    public AuthenticationResponse login(@RequestHeader(AUTH_TOKEN) String bearerToken, @RequestBody Student student) {
        return studentService.login(student);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
        return studentService.logout(refreshTokenRequest);
    }

    @GetMapping(value = "/all", produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> getAllStudents() {
        return studentService.getAllStudents();
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long id) {
        return studentService.deleteStudent(id);
    }

    @PutMapping(value = "/update", consumes = "application/json", produces="application/json")
    @ResponseBody
    public ResponseEntity<?> update(Student student){
        return studentService.updateStudent(student);
    }

    @GetMapping("/")
    public String home() {
        return "Welcome to Prograd!";
    }
}
