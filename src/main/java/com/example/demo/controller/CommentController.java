package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.commentDto.CommentRequestDto;
import com.example.demo.dto.commentDto.CommentResponseDto;
import com.example.demo.service.CommentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("")
    public ResponseEntity<CommentResponseDto> saveComment(@RequestBody CommentRequestDto commentDto){
        return(ResponseEntity.ok(commentService.save(commentDto)));
    }

}
