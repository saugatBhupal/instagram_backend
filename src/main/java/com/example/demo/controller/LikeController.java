package com.example.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.likeDto.LikeRequestDto;
import com.example.demo.dto.likeDto.LikeResponseDto;
import com.example.demo.entities.Like;
import com.example.demo.service.LikeService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/like")
@RequiredArgsConstructor
public class LikeController {
    private final LikeService likeService;

    @PostMapping("")
    public ResponseEntity<LikeResponseDto> likePost(@RequestBody LikeRequestDto likeRequestDto){
        return(ResponseEntity.ok(likeService.update(likeRequestDto)));
    }
    
    @GetMapping("")
    public ResponseEntity<List<Like>> getLikes(){
        return(ResponseEntity.ok(likeService.fetchAll()));
    }


    
}
