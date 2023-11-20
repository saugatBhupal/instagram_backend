package com.example.demo.dto.commentDto;

import java.sql.Date;

import com.example.demo.dto.UserDto;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class CommentResponseDto {

    private Long id;
    @NonNull
    private String text;
    @NonNull
    private Date commentDate;
    @NonNull
    private UserDto user;

}
