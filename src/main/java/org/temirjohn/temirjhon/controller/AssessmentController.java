package org.temirjohn.temirjhon.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.temirjohn.temirjhon.entity.Assessment;
import org.temirjohn.temirjhon.entity.Course;
import org.temirjohn.temirjhon.entity.User;
import org.temirjohn.temirjhon.request.AssessmentRequest;
import org.temirjohn.temirjhon.service.AssessmentService;
import org.temirjohn.temirjhon.service.CourseService;
import org.temirjohn.temirjhon.service.UserService;

@RestController
@RequestMapping("/api/assessments")
public class AssessmentController {

    @Autowired
    private AssessmentService assessmentService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private CourseService courseService;

    @GetMapping("/user/{userId}/course/{courseId}")
    public ResponseEntity<List<Assessment>> getAssessmentsByUserAndCourse(
            @PathVariable Long userId,
            @PathVariable Long courseId
    ) {
    	User user = userService.getUserById(userId);
        Course course = courseService.getCourseById(courseId);

        List<Assessment> assessments = assessmentService.getAssessmentsByUserAndCourse(user, course);
        return ResponseEntity.ok(assessments);
    }
    
    @GetMapping("/perfomance/{userId}")
    public ResponseEntity<List<Assessment>> getAssessmentsByUser(@PathVariable Long userId){
    	User user = userService.getUserById(userId);
    	return assessmentService.getAssessmentByUser(user);
    }

    @PostMapping("/add/{userId}/{courseId}")
    public ResponseEntity<Assessment> addAssessmentWithMarks(
            @PathVariable Long userId,
            @PathVariable Long courseId,
            @RequestBody AssessmentRequest request) {


        User user = userService.getUserById(userId);
        Course course = courseService.getCourseById(courseId);

        Assessment assessment = new Assessment();
        assessment.setMarks(request.getMarks());

        return assessmentService.saveAssessment(user, course, assessment);
    }
}
