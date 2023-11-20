package com.example.demo.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.postDto.PostRequestDto;
import com.example.demo.dto.postDto.PostResponseDto;
import com.example.demo.entities.Post;

public interface PostService {
    List<PostResponseDto> fetchAll();
    Post fetchPostById(Long postID);
    PostResponseDto fetchByPostId(Long postID);
    List<PostResponseDto> fetchPostByUserId(Long userID);
    PostResponseDto save(PostRequestDto post, MultipartFile file);
    void delete(Long postID);
    Integer save();
}
