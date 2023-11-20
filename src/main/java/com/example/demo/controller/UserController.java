package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.profile.ProfilePageResponseDto;
import com.example.demo.dto.profile.ProfileRequestDto;
import com.example.demo.dto.profile.ProfileResponseDto;
import com.example.demo.service.UserService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
@SuppressWarnings("unchecked")
public class UserController {
    private final UserService userService;

    @GetMapping("/{userID}")
    private ResponseEntity<ProfileResponseDto> getProfile(@PathVariable("userID") Long userID){
        return ResponseEntity.ok(userService.fetchProfileByID(userID));
    }

    @PostMapping("")
    private <T> ResponseEntity<T> setProfile(@RequestBody ProfileRequestDto profileRequestDto){
        T ret = (T) ResponseEntity.ok(userService.saveProfile(profileRequestDto));
        return ResponseEntity.ok(ret);
    }

    @GetMapping("/profile/{userID}")
    private ResponseEntity<ProfilePageResponseDto> getProfilePage(@PathVariable("userID") Long userID){
        return(ResponseEntity.ok(userService.fetchFullProfile(userID)));
    }
    
}
