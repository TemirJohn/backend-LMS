package org.temirjohn.temirjhon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.temirjohn.temirjhon.request.ProgressRequest;
import org.temirjohn.temirjhon.entity.Course;
import org.temirjohn.temirjhon.entity.Progress;
import org.temirjohn.temirjhon.entity.User;
import org.temirjohn.temirjhon.repository.CourseRepository;
import org.temirjohn.temirjhon.repository.ProgressRepository;
import org.temirjohn.temirjhon.repository.UserRepository;


@Service
public class ProgressService {

    @Autowired
    private ProgressRepository progressRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    public ResponseEntity<String> updateProgress(ProgressRequest request) {
        Long userId = request.getUserId();
        Long courseId = request.getCourseId();
        float playedTime = request.getPlayedTime();
        float duration = request.getDuration();

        User user = userRepository.findById(userId).orElse(null);
        Course course = courseRepository.findById(courseId).orElse(null);

        if (user != null && course != null) {
            Progress progress = progressRepository.findByUserAndCourse(user, course);
            if (progress != null && (progress.getPlayedTime() == 0 || progress.getPlayedTime()<= playedTime)) {
                progress.setPlayedTime(playedTime);
                progress.setDuration(duration);
                progressRepository.save(progress);
                return ResponseEntity.ok("success");
            } else {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Invalid playedTime");
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User or course not found");
    }

	public float getProgress(Long userId, Long courseId) {
		User user = userRepository.findById(userId).orElse(null);
        Course course = courseRepository.findById(courseId).orElse(null);

        if (user != null && course != null) {
         Progress progress = progressRepository.findByUserAndCourse(user, course);
         return progress.getPlayedTime();
        }
		return 0; 
	}

	public ResponseEntity<String> updateDuration(ProgressRequest request) {
        Long userId = request.getUserId();
        Long courseId = request.getCourseId();
        float newDuration = request.getDuration();

        User user = userRepository.findById(userId).orElse(null);
        Course course = courseRepository.findById(courseId).orElse(null);

        if (user != null && course != null) {
            Progress progress = progressRepository.findByUserAndCourse(user, course);

            if (progress != null) {
                progress.setDuration(newDuration);
                progressRepository.save(progress);

                return ResponseEntity.ok("Duration updated successfully");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Progress not found for the given user and course");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User or course not found");
        }
    }

    
}

