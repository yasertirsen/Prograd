package com.fyp.prograd.service;

import com.fyp.prograd.model.Student;
import com.fyp.prograd.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    public Student add(Student student) {
        return studentRepository.save(student);
    }

    public Student updateStudent(@RequestBody Student student) {
        return studentRepository.save(student);
    }

    public ResponseEntity<Student> findByEmail(@RequestBody String email) {
        if(studentRepository.existsByEmail(email))
            return new ResponseEntity<>(studentRepository.findByEmail(email), HttpStatus.OK);
        else
            return new ResponseEntity<>(null, HttpStatus.OK);
    }

    public ResponseEntity<Student> findByUsername(@RequestBody String username) {
        if(studentRepository.existsByUsername(username))
            return new ResponseEntity<>(studentRepository.findByUsername(username), HttpStatus.OK);
        else
            return new ResponseEntity<>(null, HttpStatus.OK);
    }

    public ResponseEntity<Student> findByToken(String token) {
        if(studentRepository.existsByToken(token))
            return new ResponseEntity<>(studentRepository.findByToken(token), HttpStatus.OK);
        else
            return new ResponseEntity<>(null, HttpStatus.OK);
    }

    public Student login(Student student) {
        return studentRepository.save(student);
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
}
