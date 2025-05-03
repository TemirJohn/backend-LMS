package org.temirjohn.temirjhon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.temirjohn.temirjhon.entity.Course;
import org.temirjohn.temirjhon.entity.Questions;


public interface QuestionRepository extends JpaRepository<Questions, Long> {

	List<Questions> findByCourse(Course course);
}
