package com.example.demo.dto.profile;

import java.util.List;

import com.example.demo.entities.User;

import io.micrometer.common.lang.NonNull;
import lombok.Data;

@Data
public class ProfilePageResponseDto {

    @NonNull
    private Long id;
    @NonNull
    private String fullname;
    @NonNull
    private String username;
    @NonNull
    private String contact;
    private String sex;
    private String bio;
    private String link;
    private Integer followerCount;
    private Integer followingCount;
    private List<ProfileResponseDto> followers;
    private List<ProfileResponseDto> following;
    private String profileImage;
}
