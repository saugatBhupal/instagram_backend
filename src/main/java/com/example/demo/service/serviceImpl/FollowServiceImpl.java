package com.example.demo.service.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.demo.dto.FollowDto.FollowRequestDto;
import com.example.demo.dto.FollowDto.FollowResponseDto;
import com.example.demo.dto.profile.ProfileResponseDto;
import com.example.demo.entities.Follow;
import com.example.demo.entities.User;
import com.example.demo.mappers.FollowMapper;
import com.example.demo.mappers.UserMapper;
import com.example.demo.repository.FollowRespository;
import com.example.demo.service.FollowService;
import com.example.demo.utils.dateTimeUtil.DateTimeUtil;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class FollowServiceImpl implements FollowService {
    private final FollowRespository respository;
    private final FollowMapper followMapper;
    private final CustomUserDetailService userService; //avoid cuircular dependency
    private final UserMapper userMapper;

    @Override
    public HashMap<String, List<Long>> fetchFollowerAndFollowingIDs(Long userID) {
        List<Follow> follows = respository.findByOwnerOrFollower(userService.findById(userID), userService.findById(userID));
        List<Long> followers = follows.stream()
                                    .filter(follow -> follow.getOwner().getId().equals(userID))
                                    .map(Follow :: getOwner)
                                    .map(User :: getId)
                                    .collect(Collectors.toList());
        List<Long> following = follows.stream()
                                    .filter(follow -> follow.getFollower().getId().equals(userID))
                                    .map(Follow :: getFollower)
                                    .map(User :: getId)
                                    .collect(Collectors.toList());      
        HashMap<String,List<Long>> followMap = new HashMap<>();
        followMap.put("followers", followers);
        System.out.println(followers);
        followMap.put("following", following);                   
        return(followMap);   
    }

    private Optional<Follow> getExistingRecord(FollowRequestDto followRequestDto){
        return(respository.findExistingRecord(followRequestDto.getOwnerID(), followRequestDto.getFollowerID()));
    }

    @Override
    public FollowResponseDto save(FollowRequestDto followRequestDto) {
        Follow follow = followMapper.toFollow(followRequestDto);
        Optional<Follow> existingRecord = getExistingRecord(followRequestDto);
        if(existingRecord.isPresent()){
            respository.delete(existingRecord.get());
            return(new FollowResponseDto());
        }
        follow.setFollowedDate(DateTimeUtil.getDate());
        follow.setFollower(userService.findById(followRequestDto.getFollowerID()));
        follow.setOwner(userService.findById(followRequestDto.getOwnerID()));
        return(followMapper.toFollowResponseDto(respository.save(follow)));
    }

    
    
}
