package com.example.demo.mappers;

import org.mapstruct.Mapper;

import com.example.demo.dto.commentDto.CommentRequestDto;
import com.example.demo.dto.commentDto.CommentResponseDto;
import com.example.demo.entities.Comment;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    
    Comment toComment(CommentRequestDto commentRequestDto);

    CommentResponseDto toCommentResponseDto(Comment comment);
}
