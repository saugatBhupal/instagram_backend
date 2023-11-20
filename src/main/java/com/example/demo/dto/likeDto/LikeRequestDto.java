package com.example.demo.dto.likeDto;

import java.sql.Date;

import io.micrometer.common.lang.NonNull;
import lombok.Data;

@Data
public class LikeRequestDto {

    private Long id;
    @NonNull
    private Long postID;
    private Date likeDate;
    @NonNull
    private Long userID;
}
