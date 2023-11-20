package com.example.demo.dto.postDto;

import java.sql.Date;
import java.util.List;

import com.example.demo.dto.commentDto.CommentResponseDto;
import com.example.demo.dto.profile.ProfileResponseDto;
import com.example.demo.entities.Like;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class PostResponseDto {
    
    private Long id;
    @NonNull
    private String content;
    @NonNull
    private String caption;
    @NonNull
    private Date postDate;

    private String location;
    @NonNull
    private ProfileResponseDto user;

    private List<CommentResponseDto> comments;

    private List<Like> likes;
}
