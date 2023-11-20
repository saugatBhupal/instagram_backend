package com.example.demo.dto.likeDto;

import java.sql.Date;

import com.example.demo.dto.profile.ProfileResponseDto;

import lombok.Data;

@Data
public class LikeResponseDto {

    private Long id;
    private Date likeDate;
    private ProfileResponseDto user;
}
