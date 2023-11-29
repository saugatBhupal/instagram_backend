package com.example.demo.dto.VerificationDto;

import com.example.demo.entities.User;

import io.micrometer.common.lang.NonNull;
import lombok.Data;

@Data
public class VerificationRequestDto {

    @NonNull
    User user;

    @NonNull
    String token;
}
