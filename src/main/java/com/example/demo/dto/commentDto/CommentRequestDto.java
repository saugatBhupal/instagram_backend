package com.example.demo.dto.commentDto;

import java.sql.Date;

import com.example.demo.dto.UserDto;

import lombok.Data;
import lombok.NonNull;

@Data
public class CommentRequestDto {
    
    private Long id;
    @NonNull
    private Long postID;
    @NonNull
    private String text;
    @NonNull
    private Date commentDate;
    @NonNull
    private UserDto user;
}
