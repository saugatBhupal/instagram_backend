package com.example.demo.service;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.CredentialsDto;
import com.example.demo.dto.SignupDto;
import com.example.demo.dto.UserDto;
import com.example.demo.dto.profile.ProfilePageResponseDto;
import com.example.demo.dto.profile.ProfileRequestDto;
import com.example.demo.dto.profile.ProfileResponseDto;
import com.example.demo.entities.User;


public interface UserService {
    UserDto find(String login);
    UserDto register(SignupDto details);
    UserDto authenticate(CredentialsDto credentialsDto);
    User findById(Long userId);
    ProfileResponseDto fetchProfileByID(Long userID);
    ProfileResponseDto updateProfilePicture(MultipartFile file, Long UserID);
    <T> T saveProfile(ProfileRequestDto profileRequestDto);
    ProfilePageResponseDto fetchFullProfile(Long userID);
}
