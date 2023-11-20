package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.dto.commentDto.CommentRequestDto;
import com.example.demo.dto.commentDto.CommentResponseDto;
import com.example.demo.entities.Comment;
import com.example.demo.entities.Post;

public interface CommentService {
    List<Comment> fetchAll();
    Optional<Comment> fetchById(Long commentID);
    List<Comment> fetchCommentByPostID(Post post);
    CommentResponseDto save(CommentRequestDto comment);
    void delete(Comment comment);
}
