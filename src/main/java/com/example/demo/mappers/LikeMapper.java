package com.example.demo.mappers;

import org.mapstruct.Mapper;

import com.example.demo.dto.likeDto.LikeRequestDto;
import com.example.demo.dto.likeDto.LikeResponseDto;
import com.example.demo.entities.Like;

@Mapper(componentModel = "spring")
public interface LikeMapper {

    Like toLike(LikeRequestDto likeRequestDto);

    LikeResponseDto toLikeResponseDto(Like like);
}
