package com.example.demo.controller;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.VerificationDto.VerificationRequestDto;
import com.example.demo.service.VerificationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/verify")
@RequiredArgsConstructor
public class VerificationController {
    private final VerificationService verificationService;

    @PostMapping("")
    public ResponseEntity<String> verifyAccount(@RequestBody VerificationRequestDto verificationRequestDto){
        verificationService.Verify(verificationRequestDto);
        return(ResponseEntity.ok("Account verified"));
    }

}
