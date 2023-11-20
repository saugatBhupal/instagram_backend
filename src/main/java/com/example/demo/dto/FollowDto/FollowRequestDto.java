package com.example.demo.dto.FollowDto;

import io.micrometer.common.lang.NonNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FollowRequestDto {

    private Long id;
    @NonNull
    private Long followerID;
    @NonNull
    private Long ownerID;
    
}
