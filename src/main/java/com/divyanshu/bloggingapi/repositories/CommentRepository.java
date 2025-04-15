package com.divyanshu.bloggingapi.repositories;

import com.divyanshu.bloggingapi.entities.Comment;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
}
