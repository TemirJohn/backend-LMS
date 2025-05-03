package org.temirjohn.temirjhon.controller;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.temirjohn.temirjhon.entity.Course;
import org.temirjohn.temirjhon.repository.CourseRepository;
import org.temirjohn.temirjhon.repository.LearningRepository;
import org.temirjohn.temirjhon.repository.ProgressRepository;
import org.temirjohn.temirjhon.service.CourseService;


@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private LearningRepository learningRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private ProgressRepository progressRepository;

    @GetMapping
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    @GetMapping("/{id}")
    public Course getCourseById(@PathVariable Long id) {
        return courseService.getCourseById(id);
    }

    @PostMapping
    public Course createCourse(@RequestBody Course course) {
        return courseService.createCourse(course);
    }

    @PostMapping("/{id}")
    public Course updateCourse(@PathVariable Long id, @RequestBody Course updatedCourse) {
        return courseService.updateCourse(id, updatedCourse);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deleteCourse(@PathVariable Long id) {
        learningRepository.deleteByCourseId(id); // сначала очищаем зависимости
        progressRepository.deleteByCourseId(id);
        courseRepository.deleteById(id);         // потом сам курс
    }
}

