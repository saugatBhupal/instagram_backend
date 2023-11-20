package com.example.demo.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {

    private Long id;
    private String fullname;
    private String username;
    private String contact;
    private String token;

}
