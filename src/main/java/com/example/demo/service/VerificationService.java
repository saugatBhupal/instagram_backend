package com.example.demo.service;

import com.example.demo.dto.VerificationDto.VerificationRequestDto;
import com.example.demo.entities.User;

public interface VerificationService {
    void Verify(VerificationRequestDto verificationRequestDto);
    void SendToken(User user);
}
