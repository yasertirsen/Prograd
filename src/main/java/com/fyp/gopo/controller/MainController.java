package com.fyp.gopo.controller;

import com.fyp.gopo.model.Course;
import com.fyp.gopo.model.Industry;
import com.fyp.gopo.model.Module;
import com.fyp.gopo.model.Resume;
import com.fyp.gopo.model.Skill;
import com.fyp.gopo.model.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class MainController {

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

        Course course = new Course("Business Computing", "Technological University Dublin", "8", "4 years", modules);

        return Arrays.asList(
                new Student("Yacer", "Tirsen", "yacer@gmail.com", "YTirsen",
                        "password1", "0877201711", "https://www.linkedin.com/", resume, course, externalSkills),
                new Student("Mihai", "Carere", "https://www.linkedin.com/", "Mihai@gmail.com", "MCarere",
                        "password1","0877210156", resume, course, externalSkills)
        );
    }

    @GetMapping("/")
    public String home() {
        return "Welcome to Gopo!";
    }
}
