package com.example.demo.service.serviceImpl;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.entities.User;
import com.example.demo.exceptions.AppException;
import com.example.demo.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService{
    private final UserRepository userRepository;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        return this.userRepository.findByUsernameOrContact(username).orElseThrow(()-> new AppException("Username not found",HttpStatus.NOT_FOUND));
    }

    public User findById(Long userId) {
       return(userRepository.findById(userId).orElseThrow(() -> new AppException("User not found", HttpStatus.NOT_FOUND)));
    }

}
