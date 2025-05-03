package org.temirjohn.temirjhon.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.temirjohn.temirjhon.request.FeedbackRequest;
import org.temirjohn.temirjhon.entity.Feedback;
import org.temirjohn.temirjhon.service.FeedbackService;


import java.util.List;

@RestController
@RequestMapping("/api/feedbacks")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @GetMapping("/{courseId}")
    public List<Feedback> getFeedbacksForCourse(@PathVariable Long courseId) {
        return feedbackService.getFeedbacksForCourse(courseId);
    }

    @PostMapping
    public String submitFeedback(@RequestBody FeedbackRequest fr) {
        return feedbackService.submitFeedback(fr);
    }
}
