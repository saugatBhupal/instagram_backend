package com.example.demo.service.serviceImpl;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.postDto.PostRequestDto;
import com.example.demo.dto.postDto.PostResponseDto;
import com.example.demo.entities.Post;
import com.example.demo.entities.User;
import com.example.demo.exceptions.AppException;
import com.example.demo.mappers.PostMapper;
import com.example.demo.repository.PostRepository;
import com.example.demo.service.PostService;
import com.example.demo.service.UserService;
import com.example.demo.utils.dateTimeUtil.DateTimeUtil;
import com.example.demo.utils.mediaUtil.MediaStorageUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository repository;
    private final PostMapper postMapper;
    private final UserService userService;

    @Override
    public List<PostResponseDto> fetchAll() {
        return(repository.findAll()
                .stream()
                .map(postMapper :: toPostResponseDto)
                .collect(Collectors.toList())
            );
    }

    @Override
    public Post fetchPostById(Long postID) {
        return(repository.findById(postID).orElseThrow(() -> new AppException("Post not found", HttpStatus.NOT_FOUND)));
    }

    @Override
    public PostResponseDto save(PostRequestDto postDto, MultipartFile file) {
        Post post = postMapper.toPost(postDto);
        post.setUser(userService.findById(postDto.getUser().getId()));
        post.setPostDate(DateTimeUtil.getDate());
        String filename = MediaStorageUtil.saveImage(file);
        if(filename == null){
            throw new AppException("Error uploading file", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        post.setContent(filename);
        PostResponseDto postRes = postMapper.toPostResponseDto(repository.save(post));
        return(postRes);
    }

    @Override
    public void delete(Long postID) {
        try{
            repository.delete(fetchPostById(postID));
        }
        catch(Exception e){
            System.out.println(e);
            throw new AppException("Could not delete the post", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /* Remove ------> */
    @Override
    public Integer save() {
        User user = userService.findById(Long.valueOf(100113));
        Post post = new Post();
        post.setCaption("trurur");
        post.setLocation("KTM");
        post.setPostDate(Date.valueOf("2023-12-20"));
        post.setContent("contnt");
        post.setUser(user);
        System.out.println(repository.findAll().size());
        return(repository.findAll().size());
    }
    /* -------> */

    @Override
    public List<PostResponseDto> fetchPostByUserId(Long userID) {
        try{
            return repository.findByUserID(userID)
                .stream()
                .map(postMapper::toPostResponseDto)
                .collect(Collectors.toList());
        }
        catch(Exception e){
            System.out.println(e);
            throw new AppException("Could not retrieve posts", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
    }

    @Override
    public PostResponseDto fetchByPostId(Long postID) {
        return(postMapper.toPostResponseDto(fetchPostById(postID)));
    }
    
}
