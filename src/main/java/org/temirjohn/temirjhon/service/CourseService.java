package org.temirjohn.temirjhon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.temirjohn.temirjhon.entity.Course;
import org.temirjohn.temirjhon.repository.CourseRepository;


import java.util.List;

@Service
public class CourseService {

@Autowired
private CourseRepository courseRepository;

public List<Course> getAllCourses() {
    return courseRepository.findAll();
}

public Course getCourseById(Long id) {
    return courseRepository.findById(id).orElse(null);
}

public Course createCourse(Course course) {
    return courseRepository.save(course);
}

    public Course updateCourse(Long id, Course updatedCourse) {
        Course existingCourse = courseRepository.findById(id).orElse(null);
        if (existingCourse != null && updatedCourse != null) {
            existingCourse.setCourseName(updatedCourse.getCourseName());
            existingCourse.setDescription(updatedCourse.getDescription());
            existingCourse.setPhoto(updatedCourse.getPhoto());
            existingCourse.setPrice(updatedCourse.getPrice()); // Оставляем, если поле price есть
            existingCourse.setTutor(updatedCourse.getTutor());
            existingCourse.setVideo(updatedCourse.getVideo());
            return courseRepository.save(existingCourse);
        }
        return null;
    }

public void deleteCourse(Long id) {
    courseRepository.deleteById(id);
}
}
