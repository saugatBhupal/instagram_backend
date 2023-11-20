package com.example.demo.dto;

import lombok.NonNull;

public record SignupDto(@NonNull String contact, @NonNull String fullname, @NonNull String username , @NonNull String password) {

    
}
