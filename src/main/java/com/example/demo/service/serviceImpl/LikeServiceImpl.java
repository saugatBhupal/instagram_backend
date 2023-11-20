package com.example.demo.service.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.demo.dto.likeDto.LikeRequestDto;
import com.example.demo.dto.likeDto.LikeResponseDto;
import com.example.demo.entities.Like;
import com.example.demo.exceptions.AppException;
import com.example.demo.mappers.LikeMapper;
import com.example.demo.repository.LikeRepository;
import com.example.demo.service.LikeService;
import com.example.demo.service.PostService;
import com.example.demo.service.UserService;
import com.example.demo.utils.dateTimeUtil.DateTimeUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService{
    private final LikeRepository repository;
    private final PostService postService;
    private final UserService userService;
    private final LikeMapper likeMapper;

    @Override
    public List<Like> fetchAll() {
        return(repository.findAll());
    }

    @Override
    public Optional<Like> fetchLikeById(Long likeID) {
        return(repository.findById(likeID));
    }

    @Override
    public void delete(Like like) {
        try{
            repository.delete(like);
        }
        catch(Exception e){
            System.out.println(e);
            throw new AppException("Error deleting post", HttpStatus.CONFLICT);
        }
        
    }

    private Optional<Like> hasLiked(Long userID, Long postID){
        return(repository.findByUserIDandPostID(userID,postID));
    }

    @Override
    public LikeResponseDto update(LikeRequestDto likeRequestDto) {
        Like like = likeMapper.toLike(likeRequestDto);
        like.setPost(postService.fetchPostById(likeRequestDto.getPostID()));
        like.setUser(userService.findById(likeRequestDto.getUserID()));
        like.setLikeDate(DateTimeUtil.getDate());
        Optional<Like> hasliked = hasLiked(likeRequestDto.getUserID(), likeRequestDto.getPostID());
        if(hasliked.isPresent()){
            delete(hasliked.get());
            return(new LikeResponseDto());
        }
        return(likeMapper.toLikeResponseDto(repository.save(like)));
    }

    
}
