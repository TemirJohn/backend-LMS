package org.temirjohn.temirjhon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.temirjohn.temirjhon.entity.Assessment;
import org.temirjohn.temirjhon.entity.Course;
import org.temirjohn.temirjhon.entity.User;

public interface AssessmentRepository extends JpaRepository<Assessment, Long> {

    List<Assessment> findByUserAndCourse(User user, Course course);

	List<Assessment> findByUser(User user);
}
