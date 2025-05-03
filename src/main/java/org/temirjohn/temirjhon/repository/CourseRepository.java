package org.temirjohn.temirjhon.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.temirjohn.temirjhon.entity.Course;


public interface CourseRepository extends JpaRepository<Course, Long> {
}