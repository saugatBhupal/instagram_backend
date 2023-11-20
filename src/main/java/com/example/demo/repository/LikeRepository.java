package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entities.Like;
import java.util.Optional;


public interface LikeRepository extends JpaRepository<Like,Long>{
    
    @Query(value = "select * from likes where userid = ? and postid = ?",nativeQuery = true)
    Optional<Like> findByUserIDandPostID(Long userId, Long postID);
}
