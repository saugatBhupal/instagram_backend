package com.example.demo.service;

import java.util.HashMap;
import java.util.List;

import com.example.demo.dto.FollowDto.FollowRequestDto;
import com.example.demo.dto.FollowDto.FollowResponseDto;
import com.example.demo.dto.profile.ProfileResponseDto;
import com.example.demo.entities.User;

public interface FollowService {
    HashMap<String, List<Long>> fetchFollowerAndFollowingIDs(Long userID);
    FollowResponseDto save(FollowRequestDto followRequestDto);
    
}
