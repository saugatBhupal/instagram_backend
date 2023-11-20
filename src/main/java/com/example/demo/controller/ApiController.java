package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.example.demo.dto.CredentialsDto;
import com.example.demo.dto.SignupDto;
import com.example.demo.dto.UserDto;
import com.example.demo.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ApiController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody @Valid SignupDto signupDetails) {
        UserDto userDto = userService.register(signupDetails);
        return ResponseEntity.ok(userDto);
    }

    @PostMapping("/auth")
    public ResponseEntity<UserDto> login(@RequestBody @Valid CredentialsDto credentialsDto){
        return ResponseEntity.ok(userService.authenticate(credentialsDto));
    }

    @GetMapping("/validate")
    public ResponseEntity<String> validateToken(){
        return ResponseEntity.ok("Token validated");
    }
    
}
