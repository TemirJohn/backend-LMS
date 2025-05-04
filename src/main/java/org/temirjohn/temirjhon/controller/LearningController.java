package org.temirjohn.temirjhon.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.temirjohn.temirjhon.request.EnrollRequest;
import org.temirjohn.temirjhon.entity.Course;
import org.temirjohn.temirjhon.entity.Learning;
import org.temirjohn.temirjhon.service.LearningService;


import java.util.List;

@RestController
@RequestMapping("/api/learning")
public class LearningController {

    @Autowired
    private LearningService learningService;

    @GetMapping("/{userId}")
    public List<Course> getLearningCourses(@PathVariable Long userId) {
        return learningService.getLearningCourses(userId);
    }
    
    @GetMapping
    public List<Learning> getEnrollments() {
        return learningService.getEnrollments();
    }

    @DeleteMapping
    public ResponseEntity<String> unenroll(@RequestParam Long userId, @RequestParam Long courseId) {
        try {
            learningService.unenroll(userId, courseId);
            return ResponseEntity.ok("Unenrolled successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Enrollment not found");
        }
    }

    @PostMapping
    public String enrollCourse(@RequestBody EnrollRequest enrollRequest) {
    	System.out.println(enrollRequest.getCourseId() +" = "+enrollRequest.getUserId());
        return learningService.enrollCourse(enrollRequest);
    }

    @DeleteMapping("/{id}")
    public void unenrollCourse(@PathVariable Long id) {
        learningService.unenrollCourse(id);
    }
}
