package com.example.demo.dto.FollowDto;

import java.sql.Date;

import com.example.demo.dto.profile.ProfileResponseDto;

import lombok.Data;

@Data
public class FollowResponseDto {

    private Long id;
    private Date followDate;
    private ProfileResponseDto follower;
    private ProfileResponseDto owner;
}
