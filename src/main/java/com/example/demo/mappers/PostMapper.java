package com.example.demo.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.demo.dto.postDto.PostRequestDto;
import com.example.demo.dto.postDto.PostResponseDto;
import com.example.demo.entities.Post;

@Mapper(componentModel = "spring")
public interface PostMapper {

    Post toPost(PostRequestDto postDto);
    Post toPost(PostResponseDto postDto);

    PostRequestDto toPostRequestDto(Post post);
    PostRequestDto toPostRequestDto(PostResponseDto post);

    PostResponseDto toPostResponseDto(Post post);
    PostResponseDto toPostResponseDto(PostRequestDto post);

    
}
