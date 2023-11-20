package com.example.demo.controller;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.FollowDto.FollowRequestDto;
import com.example.demo.dto.FollowDto.FollowResponseDto;
import com.example.demo.service.FollowService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/follow")
public class FollowController {
    private final FollowService followService;

    @PostMapping("")
    public ResponseEntity<FollowResponseDto> follow(@RequestBody FollowRequestDto followRequestDto){
        return(ResponseEntity.ok(followService.save(followRequestDto)));
    }
}
