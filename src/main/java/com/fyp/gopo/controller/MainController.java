package com.fyp.gopo.controller;

import com.fyp.gopo.model.Course;
import com.fyp.gopo.model.Industry;
import com.fyp.gopo.model.Module;
import com.fyp.gopo.model.Resume;
import com.fyp.gopo.model.Skill;
import com.fyp.gopo.model.Student;
import com.fyp.gopo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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
public class MainController {

    @RequestMapping("/students")
    public List<Student> getAllStudents() {
        Industry techIndustry = new Industry("Technology");

        Set<Skill> skills1 = new HashSet<Skill>();
        Set<Skill> skills2 = new HashSet<Skill>();
        Set<Skill> externalSkills= new HashSet<Skill>();
        skills1.add(new Skill("Java", techIndustry));
        skills1.add(new Skill("Object Oriented Software Development", techIndustry));
        skills2.add(new Skill("Python", techIndustry));
        externalSkills.add(new Skill("Angular", techIndustry));
        externalSkills.add(new Skill("Spring Boot", techIndustry));

        Set<Module> modules= new HashSet<Module>();
        modules.add(new Module("OOSD", "To learn Object Oriented Software development through Java", skills1));
        modules.add(new Module("Dynamic Programming Language", "To learn DPL through Python", skills2));



        Resume resume = new Resume("CONTENT PLACEHOLDER");

        Course course = new Course("Business Computing", "Technological University Dublin", "8", "4 years", modules);

        return Arrays.asList(
                new Student("Yacer", "Tirsen", "yacer@gmail.com", "YTirsen",
                        "password1", "0877201711", resume, course, externalSkills),
                new Student("Mihai", "Carere", "Mihai@gmail.com", "MCarere",
                        "password1","0877210156", resume, course, externalSkills)
        );
    }

    @GetMapping("/")
    public String home() {
        return "Welcome to Gopo!";
    }
}
