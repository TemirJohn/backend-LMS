package org.temirjohn.temirjhon.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.temirjohn.temirjhon.entity.Course;
import org.temirjohn.temirjhon.entity.Discussion;


public interface DiscussionRepository extends JpaRepository<Discussion, Long> {

    List<Discussion> findByCourse(Course course);
}
