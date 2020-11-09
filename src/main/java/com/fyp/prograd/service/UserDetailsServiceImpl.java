package com.fyp.prograd.service;

import com.fyp.prograd.model.Company;
import com.fyp.prograd.model.Student;
import com.fyp.prograd.repository.CompanyRepository;
import com.fyp.prograd.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import static java.util.Collections.singletonList;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final StudentRepository studentRepository;
    private final CompanyRepository companyRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Student> studentOptional = studentRepository.findByEmail(email);
        if(!studentOptional.isPresent()) {
            Optional<Company> companyOptional = companyRepository.findByEmail(email);
            Company company = companyOptional.orElseThrow(() -> new UsernameNotFoundException("No company found with email" +
                    email));

            return new User(company.getEmail(), company.getPassword(),
                    company.isEnabled(), true, true,
                    true, getAuthorities("COMPANY"));
        }
        else {
            Student student = studentOptional.orElseThrow(() -> new UsernameNotFoundException("No student found with username" +
                    email));

            return new User(student.getUsername(), student.getPassword(),
                    student.isEnabled(), true, true,
                    true, getAuthorities("STUDENT"));
        }
    }

    private Collection<? extends GrantedAuthority> getAuthorities(String role) {
        return singletonList(new SimpleGrantedAuthority(role));
    }
}
