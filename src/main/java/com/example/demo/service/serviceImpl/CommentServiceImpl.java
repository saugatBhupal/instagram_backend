package com.example.demo.service.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.demo.dto.commentDto.CommentRequestDto;
import com.example.demo.dto.commentDto.CommentResponseDto;
import com.example.demo.entities.Comment;
import com.example.demo.entities.Post;
import com.example.demo.exceptions.AppException;
import com.example.demo.mappers.CommentMapper;
import com.example.demo.repository.CommentRepository;
import com.example.demo.service.CommentService;
import com.example.demo.service.PostService;
import com.example.demo.service.UserService;
import com.example.demo.utils.dateTimeUtil.DateTimeUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository repository;
    private final CommentMapper commentMapper;
    private final PostService postService;
    private final UserService userService;

    @Override
    public CommentResponseDto save(CommentRequestDto commentReq) {
        Comment comment = commentMapper.toComment(commentReq);
        comment.setPost(postService.fetchPostById(commentReq.getPostID()));
        comment.setUser(userService.findById(commentReq.getUser().getId()));
        comment.setCommentDate(DateTimeUtil.getDate());
        return(commentMapper.toCommentResponseDto(repository.save(comment)));

    }

    @Override
    public void delete(Comment comment) {
        try{
            repository.delete(comment);
        }
        catch(Exception e){
            System.out.println(e);
            throw new AppException("Could Not Delete Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Optional<Comment> fetchById(Long commentID) {
        return(repository.findById(commentID));
    }


    @Override
    public List<Comment> fetchCommentByPostID(Post post) {
        return(repository.findByPost(post));
    }

    @Override
    public List<Comment> fetchAll() {
        return(repository.findAll());
    }
    
}
