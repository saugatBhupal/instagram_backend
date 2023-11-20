package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Comment;
import com.example.demo.entities.Post;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long>{
    
    List<Comment> findByPost(Post post);
}
