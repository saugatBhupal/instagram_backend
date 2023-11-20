package com.example.demo.dto.postDto;

import java.sql.Date;

import com.example.demo.dto.UserDto;
import lombok.Data;
import lombok.NonNull;

@Data
public class PostRequestDto {

    private Long id;
    @NonNull
    private String caption;
    @NonNull
    private Date postDate;
    @NonNull
    private String location;
    @NonNull
    private UserDto user;

}
