package org.temirjohn.temirjhon.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.temirjohn.temirjhon.entity.Course;
import org.temirjohn.temirjhon.entity.Discussion;
import org.temirjohn.temirjhon.repository.DiscussionRepository;


@Service
public class DiscussionService {

    @Autowired
    private DiscussionRepository discussionRepository;

    public List<Discussion> getDiscussionsCourse(Course course) {
        return discussionRepository.findByCourse(course);
    }
    public Discussion createDiscussion(Discussion discussion) {
        return discussionRepository.save(discussion);
    }
}
