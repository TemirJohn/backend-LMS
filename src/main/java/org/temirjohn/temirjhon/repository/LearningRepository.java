package org.temirjohn.temirjhon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.temirjohn.temirjhon.entity.Course;
import org.temirjohn.temirjhon.entity.Learning;
import org.temirjohn.temirjhon.entity.User;




public interface LearningRepository extends JpaRepository<Learning, Long> {

	Learning findByUserAndCourse(User user, Course course);

	void deleteByCourseId(Long id);

	boolean existsByUserIdAndCourseId(Long userId, Long courseId);

	void deleteByUserIdAndCourseId(Long userId, Long courseId);
}