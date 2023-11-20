package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entities.Follow;
import com.example.demo.entities.User;

import java.util.List;
import java.util.Optional;


public interface FollowRespository extends JpaRepository<Follow, Long>{ 
    
    @Query(value = "select * from follow where follower_id = ?1 or owner_id = ?1", nativeQuery = true)
    List<Follow> findFollow(Long userID);

    @Query(value = "select * from follow where owner_id = ? and follower_id = ?", nativeQuery = true)
    Optional<Follow> findExistingRecord(Long ownerID, Long followerID);

    List<Follow> findByOwnerOrFollower(User owner, User Follower);
}
