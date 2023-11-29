package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entities.User;
import com.example.demo.entities.Verification;
import java.util.List;
import java.util.Optional;


public interface VerificationRepository extends JpaRepository<Verification, Long>{
    
    @Query
    Optional<Verification> findByUser(User user);

    @Modifying
    @Query(value = "UPDATE verification SET verified = true WHERE (userid = ?);",nativeQuery = true)
    void updateVerification(Long userID);
}
