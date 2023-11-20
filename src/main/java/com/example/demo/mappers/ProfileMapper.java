package com.example.demo.mappers;

import org.mapstruct.Mapper;

import com.example.demo.dto.profile.ProfilePageResponseDto;
import com.example.demo.dto.profile.ProfileResponseDto;
import com.example.demo.dto.profile.ProfileResponseWithUserDto;

@Mapper(componentModel = "spring")
public interface ProfileMapper {

    ProfileResponseWithUserDto toProfileResponseWithUserDto(ProfileResponseDto profileResponseDto);

    ProfilePageResponseDto tProfilePageResponseDto(ProfileResponseDto profileResponseDto);
    
}
