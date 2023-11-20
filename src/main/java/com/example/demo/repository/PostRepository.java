package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entities.Post;

public interface PostRepository extends JpaRepository<Post,Long> {
    
    @Query(value = "select * from post where userid = ?",nativeQuery = true)
    public List<Post> findByUserID(Long userID);
}
