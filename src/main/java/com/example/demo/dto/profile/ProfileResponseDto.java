package com.example.demo.dto.profile;

import lombok.Data;
import lombok.NonNull;

@Data
public class ProfileResponseDto {

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
    private String profileImage;
}
