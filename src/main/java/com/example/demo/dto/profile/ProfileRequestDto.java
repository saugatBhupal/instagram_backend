package com.example.demo.dto.profile;

import io.micrometer.common.lang.NonNull;
import lombok.Data;

@Data
public class ProfileRequestDto {
    
    @NonNull
    private Long id;
    @NonNull
    private String fullname;
    @NonNull
    private String username;
    @NonNull
    private String password;
    @NonNull
    private String contact;
    @NonNull
    private String sex;
    @NonNull
    private String bio;
    private String link;
}
