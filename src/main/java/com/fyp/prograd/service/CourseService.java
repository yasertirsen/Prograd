package com.fyp.prograd.service;

import com.fyp.prograd.exceptions.CourseNotFoundException;
import com.fyp.prograd.model.Course;
import com.fyp.prograd.model.Module;
import com.fyp.prograd.repository.CourseRepository;
import com.fyp.prograd.repository.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final ModuleRepository moduleRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository, ModuleRepository moduleRepository) {
        this.courseRepository = courseRepository;
        this.moduleRepository = moduleRepository;
    }

    public Course add(Course course) {
        if(courseRepository.existsByNameAndUniversity(course.getName(), course.getUniversity()))
            return courseRepository.findByNameAndUniversity(course.getName(), course.getUniversity());
        else
            return courseRepository.save(course);
    }

    public List<Course> getAll() {
        return courseRepository.findAll();
    }

    public Course findById(Long id) throws CourseNotFoundException {
        if(courseRepository.existsById(id))
            return courseRepository.findByCourseId(id);
        else
            throw new CourseNotFoundException();
    }

    public boolean existsByNameAndUniversity(Course course) {
        return courseRepository.existsByNameAndUniversity(course.getName(), course.getUniversity());
    }

    public Course findByNameAndUniversity(Course course) {
        return courseRepository.findByNameAndUniversity(course.getName(), course.getUniversity());
    }

    public Course update(Course course) throws CourseNotFoundException {
        if(courseRepository.existsById(course.getCourseId())) {
            Set<Module> modules = course.getModules();
            for(Module module : modules) {
                if(moduleRepository.existsById(module.getModuleId())) {
                    modules.add(module);
                }
            }
            course.setModules(new HashSet<Module>(moduleRepository.saveAll(modules)));
            return courseRepository.save(course);
        }
        else
            throw new CourseNotFoundException();
    }
}
