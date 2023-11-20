package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.dto.likeDto.LikeRequestDto;
import com.example.demo.dto.likeDto.LikeResponseDto;
import com.example.demo.entities.Like;

public interface LikeService {
    List<Like> fetchAll();
    Optional<Like> fetchLikeById(Long likeID);
    LikeResponseDto update(LikeRequestDto like);
    void delete(Like like);
    
}
