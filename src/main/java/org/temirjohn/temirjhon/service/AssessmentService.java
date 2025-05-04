package org.temirjohn.temirjhon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.temirjohn.temirjhon.entity.Assessment;
import org.temirjohn.temirjhon.entity.Course;
import org.temirjohn.temirjhon.entity.User;
import org.temirjohn.temirjhon.repository.AssessmentRepository;


@Service
public class AssessmentService {

    @Autowired
    private AssessmentRepository assessmentRepository;
    
    public List<Assessment> getAssessmentsByUserAndCourse(User user, Course course) {
        return assessmentRepository.findByUserAndCourse(user, course);
    }
    
    public ResponseEntity<List<Assessment>> getAssessmentByUser(User user){
    	return ResponseEntity.status(HttpStatus.CREATED).body(assessmentRepository.findByUser(user));
    }
    public Assessment createAssessment(Assessment assessment) {
        return assessmentRepository.save(assessment);
    }
    public void addMarks(Assessment assessment, int marks) {
        assessment.setMarks(marks);
    }

    public ResponseEntity<Assessment> saveAssessment(User user, Course course, Assessment assessment) {
        List<Assessment> existingAssessments = getAssessmentsByUserAndCourse(user, course);

        if (!existingAssessments.isEmpty()) {
            Assessment existingAssessment = existingAssessments.get(0);
            int newMarks = assessment.getMarks();

            // ✅ если оценка выше — обновляем
            if (newMarks > existingAssessment.getMarks()) {
                addMarks(existingAssessment, newMarks);
                Assessment updated = createAssessment(existingAssessment);
                return ResponseEntity.status(HttpStatus.CREATED).body(updated);
            } else {
                // ✅ даже если не обновляем — возвращаем OK
                return ResponseEntity.ok(existingAssessment);
            }
        } else {
            // 🆕 Первая попытка
            assessment.setUser(user);
            assessment.setCourse(course);
            Assessment saved = createAssessment(assessment);
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);
        }
    }
}
