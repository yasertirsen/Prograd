package com.fyp.prograd.service;

import com.fyp.prograd.exceptions.UserNotFoundException;
import com.fyp.prograd.model.Skill;
import com.fyp.prograd.model.Student;
import com.fyp.prograd.model.StudentProfile;
import com.fyp.prograd.repository.SkillRepository;
import com.fyp.prograd.repository.StudentProfileRepository;
import com.fyp.prograd.repository.StudentRepository;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentProfileRepository profileRepository;
    private final SkillRepository skillRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository, StudentProfileRepository profileRepository, SkillRepository skillRepository) {
        this.studentRepository = studentRepository;
        this.profileRepository = profileRepository;
        this.skillRepository = skillRepository;
    }


    public Student add(Student student) {
        return studentRepository.save(student);
    }

    public Student updateStudent(Student student) throws UserNotFoundException {
        if(studentRepository.existsById(student.getStudentId())) {
            return studentRepository.save(student);
        }
        throw new UserNotFoundException();
    }

    public StudentProfile updateProfile(StudentProfile profile) throws UserNotFoundException {
        Set<Skill> skills = new HashSet<>();
        if(profileRepository.existsById(profile.getProfileId())) {
            profile.setExternalSkills(checkSkills(profile.getExternalSkills()));
            return profileRepository.save(profile);
        }
        throw new UserNotFoundException();
    }

    public Set<Skill> checkSkills(Set<Skill> skills) {
        Set<Skill> dbSkills = new HashSet<>();
        for(Skill skill : skills) {
            if(skillRepository.existsBySkillName(skill.getSkillName()))
                dbSkills.add(skillRepository.findTopBySkillName(skill.getSkillName()));
            else
                dbSkills.add(skillRepository.save(skill));
        }
        return dbSkills;
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

    public ResponseEntity<Student> findByToken(String token) throws UserNotFoundException {
        if(studentRepository.existsByToken(token))
            return new ResponseEntity<>(studentRepository.findByToken(token), HttpStatus.OK);
        else
            throw new UserNotFoundException("Cannot find user with token");
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

    public List<StudentProfile> getAllProfiles() {
        return profileRepository.findAll();
    }

    public List<String> getSkillsNames(StudentProfile profile) {
        List<String> skillNames = new ArrayList<>();
        for(Skill skill : profile.getExternalSkills()) {
            skillNames.add(skill.getSkillName());
        }
        return skillNames;
    }

    public Set<Skill> getAllSkills() {
        return Sets.newHashSet(skillRepository.findAll());
    }

    public Student findById(Long studentId) {
        return studentRepository.findByStudentId(studentId);
    }
}
