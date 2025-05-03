package org.temirjohn.temirjhon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.temirjohn.temirjhon.entity.Feedback;


public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
}
