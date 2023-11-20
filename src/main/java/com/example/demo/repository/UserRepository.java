package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entities.User;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
    
    @Query(value = "Select * from user where contact = ?1 or username = ?1", nativeQuery = true)
    Optional<User> findByUsernameOrContact(String UID);
    
}
