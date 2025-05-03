package org.temirjohn.temirjhon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.temirjohn.temirjhon.request.FeedbackRequest;
import org.temirjohn.temirjhon.entity.Course;
import org.temirjohn.temirjhon.entity.Feedback;
import org.temirjohn.temirjhon.repository.CourseRepository;
import org.temirjohn.temirjhon.repository.FeedbackRepository;


import java.util.List;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;


    @Autowired
    private CourseRepository courseRepository;

    public List<Feedback> getFeedbacksForCourse(Long courseId) {
        Course course = courseRepository.findById(courseId).orElse(null);
        if (course != null) {
            return course.getFeedbacks();
        }
        return null;
    }

    public String submitFeedback(FeedbackRequest fr) {
        Course course = courseRepository.findById(fr.getCourse_id()).orElse(null);
        Feedback feedback = new Feedback();

        if (course != null) {
            feedback.setCourse(course);
            feedback.setComment(fr.getComment());
            feedbackRepository.save(feedback);
            return "feedback submitted successfully";
        }
        return "feedback submition failed";
    }
}

