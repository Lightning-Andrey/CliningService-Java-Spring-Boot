package com.example.CleaningService.Repositories;

import com.example.CleaningService.Models.CleaningRequest;
import com.example.CleaningService.Models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> findByCleaningRequest(CleaningRequest cleaningRequest);
}
