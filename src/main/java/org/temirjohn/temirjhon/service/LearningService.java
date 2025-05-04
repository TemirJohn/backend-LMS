package org.temirjohn.temirjhon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.temirjohn.temirjhon.request.EnrollRequest;
import org.temirjohn.temirjhon.entity.Course;
import org.temirjohn.temirjhon.entity.Learning;
import org.temirjohn.temirjhon.entity.Progress;
import org.temirjohn.temirjhon.entity.User;
import org.temirjohn.temirjhon.repository.CourseRepository;
import org.temirjohn.temirjhon.repository.LearningRepository;
import org.temirjohn.temirjhon.repository.ProgressRepository;
import org.temirjohn.temirjhon.repository.UserRepository;

import java.util.*;

@Service
public class LearningService {

    @Autowired
    private LearningRepository learningRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;
    
    @Autowired
    private ProgressRepository progressRepository;

    public List<Course> getLearningCourses(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            List<Course> learningCourses = new ArrayList<>();

            for (Learning learning : user.getLearningCourses()) {
                Course course = learning.getCourse();
                learningCourses.add(course);
            }

            return learningCourses;
        }

        return null;
    }
    
    public List<Learning> getEnrollments() {
    	return learningRepository.findAll();
    }

    public String enrollCourse(EnrollRequest enrollRequest) {
        User user = userRepository.findById(enrollRequest.getUserId()).orElse(null);
        Course course = courseRepository.findById(enrollRequest.getCourseId()).orElse(null);

        if (user != null && course != null) {
            Learning existingLearning = learningRepository.findByUserAndCourse(user, course);
            if (existingLearning != null) {
                return "Course already enrolled";
            }

            Progress progress = new Progress();
            progress.setUser(user);
            progress.setCourse(course);
            progressRepository.save(progress);

            Learning learning = new Learning();
            learning.setUser(user);
            learning.setCourse(course);
            learningRepository.save(learning);

            return "Enrolled successfully";
        }

        return "Failed to enroll";
    }

    @Transactional
    public void unenroll(Long userId, Long courseId) {
        if (learningRepository.existsByUserIdAndCourseId(userId, courseId)) {
            learningRepository.deleteByUserIdAndCourseId(userId, courseId);
        } else {
            throw new IllegalArgumentException("Enrollment not found");
        }
    }


    public void unenrollCourse(Long id) {
        learningRepository.deleteById(id);
    }
}

