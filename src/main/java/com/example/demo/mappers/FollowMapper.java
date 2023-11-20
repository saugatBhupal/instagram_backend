package com.example.demo.mappers;

import org.mapstruct.Mapper;

import com.example.demo.dto.FollowDto.FollowRequestDto;
import com.example.demo.dto.FollowDto.FollowResponseDto;
import com.example.demo.entities.Follow;

@Mapper(componentModel = "spring")
public interface FollowMapper {
    
    FollowResponseDto toFollowResponseDto(Follow follow);
    
    Follow toFollow(FollowRequestDto followRequestDto);
}
