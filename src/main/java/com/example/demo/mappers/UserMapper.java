package com.example.demo.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.dto.SignupDto;
import com.example.demo.dto.UserDto;
import com.example.demo.dto.profile.ProfileRequestDto;
import com.example.demo.dto.profile.ProfileResponseDto;
import com.example.demo.dto.profile.ProfileResponseWithUserDto;
import com.example.demo.entities.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toUserDto(User user);

    @Mapping(target = "password", ignore = true)
    User toUser(SignupDto signUpDto);

    User toUser(ProfileRequestDto profileRequestDto);

    @Mapping(target = "followingCount", ignore = true)
    @Mapping(target = "followerCount", ignore = true)
    ProfileResponseDto toProfileResponseDto(User user);



}
