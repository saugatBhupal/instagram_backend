package com.example.demo.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.postDto.PostRequestDto;
import com.example.demo.dto.postDto.PostResponseDto;
import com.example.demo.service.PostService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {
    private final PostService postService;

    @GetMapping("")
    public ResponseEntity<List<PostResponseDto>> getPost(){
        return(ResponseEntity.ok(postService.fetchAll()));
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<PostResponseDto>> getPostByUserID(@PathVariable("id") Long userID){
        return(ResponseEntity.ok(postService.fetchPostByUserId(userID)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponseDto> getPostByID(@PathVariable("id") Long postID){
        return(ResponseEntity.ok(postService.fetchByPostId(postID)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePostByID(@PathVariable("id") Long postID){
        postService.delete(postID);
        return(ResponseEntity.ok("success"));
    }

    @PostMapping("")
    public ResponseEntity<PostResponseDto> uploadPost(@RequestPart("file") MultipartFile file,@RequestPart("post") PostRequestDto postRequestDto){
        return(ResponseEntity.ok(postService.save(postRequestDto,file)));
    }
}
