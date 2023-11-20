package com.example.demo.dto.profile;

import com.example.demo.dto.UserDto;

import lombok.Data;
import lombok.NonNull;
@Data
public class ProfileResponseWithUserDto {
    
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
    private String profileImage;
    private UserDto userDto;
}
