package org.temirjohn.temirjhon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.temirjohn.temirjhon.entity.Course;
import org.temirjohn.temirjhon.entity.Progress;
import org.temirjohn.temirjhon.entity.User;


public interface ProgressRepository extends JpaRepository<Progress, Long> {

	Progress findByUserAndCourse(User user, Course course);
}
