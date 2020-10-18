package com.fyp.gopo.controller;

import javax.validation.Valid;

import com.fyp.gopo.model.Course;
import com.fyp.gopo.model.Industry;
import com.fyp.gopo.model.Module;
import com.fyp.gopo.model.Resume;
import com.fyp.gopo.model.Skill;
import com.fyp.gopo.model.Student;
import com.fyp.gopo.repository.CourseRepository;
import com.fyp.gopo.repository.IndustryRepository;
import com.fyp.gopo.repository.ModuleRepository;
import com.fyp.gopo.repository.SkillRepository;
import com.fyp.gopo.repository.StudentRepository;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class MainController {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final ModuleRepository moduleRepository;
    private final SkillRepository skillRepository;
    private final IndustryRepository industryRepository;

    @Autowired
    public MainController(StudentRepository studentRepository, CourseRepository courseRepository, ModuleRepository moduleRepository, SkillRepository skillRepository, IndustryRepository industryRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.moduleRepository = moduleRepository;
        this.skillRepository = skillRepository;
        this.industryRepository = industryRepository;
    }

    @RequestMapping("/students")
    public List<Student> getAllStudents() {
        Industry techIndustry = new Industry("Technology");

        Set<Skill> skills1 = new HashSet<>();
        Set<Skill> skills2 = new HashSet<>();
        Set<Skill> externalSkills= new HashSet<>();
        skills1.add(new Skill("Java", techIndustry));
        skills1.add(new Skill("Object Oriented Software Development", techIndustry));
        skills2.add(new Skill("Python", techIndustry));
        externalSkills.add(new Skill("Angular", techIndustry));
        externalSkills.add(new Skill("Spring Boot", techIndustry));

        Set<Module> modules= new HashSet<>();
        modules.add(new Module("OOSD", "To learn Object Oriented Software development through Java", skills1));
        modules.add(new Module("Dynamic Programming Language", "To learn DPL through Python", skills2));



        Resume resume = new Resume("CONTENT PLACEHOLDER");

        Course course = new Course("Business Computing", "Technological University Dublin", 8, modules);

        return Arrays.asList(
                new Student("Yacer", "Tirsen", "yacer@gmail.com", "YTirsen",
                        "password1", "0877201711", "https://www.linkedin.com/", resume, course, externalSkills),
                new Student("Mihai", "Carere", "https://www.linkedin.com/", "Mihai@gmail.com", "MCarere",
                        "password1","0877210156", resume, course, externalSkills)
        );
    }

    @PostMapping(value = "/addstudent", consumes = "application/json")
    @ResponseBody
    public ResponseEntity<?> addStudent(@Valid @RequestBody Student student){
        return new ResponseEntity<>(studentRepository.save(student), HttpStatus.CREATED);
    }

    @PostMapping(value = "/addcourse", consumes = "application/json")
    @ResponseBody
    public ResponseEntity<?> addCourse(@Valid @RequestBody Course course){
        return new ResponseEntity<>(courseRepository.save(course), HttpStatus.CREATED);
    }

    @PostMapping(value = "/addmodule", consumes = "application/json")
    @ResponseBody
    public ResponseEntity<?> addModule(@Valid @RequestBody Module module){
        return new ResponseEntity<>(moduleRepository.save(module), HttpStatus.CREATED);
    }

    @PostMapping(value = "/addskill", consumes = "application/json")
    @ResponseBody
    public ResponseEntity<?> addSkill(@Valid @RequestBody Skill skill){
        return new ResponseEntity<>(skillRepository.save(skill), HttpStatus.CREATED);
    }

    @PostMapping(value = "/addindustry", consumes = "application/json")
    @ResponseBody
    public ResponseEntity<?> addIndustry(@Valid @RequestBody Industry industry){
        return new ResponseEntity<>(industryRepository.save(industry), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public String home() {
        return "Welcome to Gopo!";
    }
}
