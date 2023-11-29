package com.example.demo.service.serviceImpl;

import java.nio.CharBuffer;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.security.authentication.AuthenticationManager;
import com.example.demo.config.PasswordEncoderConfig;
import com.example.demo.dto.CredentialsDto;
import com.example.demo.dto.SignupDto;
import com.example.demo.dto.UserDto;
import com.example.demo.dto.profile.ProfilePageResponseDto;
import com.example.demo.dto.profile.ProfileRequestDto;
import com.example.demo.dto.profile.ProfileResponseDto;
import com.example.demo.dto.profile.ProfileResponseWithUserDto;
import com.example.demo.entities.User;
import com.example.demo.exceptions.AppException;
import com.example.demo.mappers.ProfileMapper;
import com.example.demo.mappers.UserMapper;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtService;
import com.example.demo.service.FollowService;
import com.example.demo.service.MailService;
import com.example.demo.service.UserService;
import com.example.demo.service.VerificationService;
import com.example.demo.utils.dateTimeUtil.DateTimeUtil;
import com.example.demo.utils.generatorUtil.VerificationTokenGenerator;
import com.example.demo.utils.mediaUtil.MediaStorageUtil;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final FollowService followService;
    private final VerificationService verificationService;
    private final MailService mailService;
    private final UserMapper userMapper;
    private final ProfileMapper profileMapper;
    private final AuthenticationManager authenticationManager;

    @Override
    public UserDto find(String login) {
        User user = userRepository.findByUsernameOrContact(login).orElseThrow(() -> new AppException("Unknown user",HttpStatus.NOT_FOUND));
        return(userMapper.toUserDto(user));
    }

    @Override
    public UserDto register(SignupDto details) {
        
        if(userRepository.findByUsernameOrContact(details.contact()).isPresent() || userRepository.findByUsername(details.contact()).isPresent()){
            throw new AppException("Username already exists", HttpStatus.CONFLICT);
        }
        else{
            try{
                User user = userMapper.toUser(details);
                user.setPassword(PasswordEncoderConfig.getInstance().encode(CharBuffer.wrap(details.password())));
                user.setJoinedDate(DateTimeUtil.getDate());
                User registered = userRepository.save(user);
                try{
                    verificationService.SendToken(user);
                }
                catch(Exception e){
                    e.printStackTrace();
                    throw new AppException("Unable to send mail to the given address. Please recheck your address.", HttpStatus.RESET_CONTENT);
                }
                return(userMapper.toUserDto(registered));
            }
            catch(Exception e){
                e.printStackTrace();
                throw new AppException("Could not create account", HttpStatus.NOT_ACCEPTABLE);
            }
        }
    }

    @Override
    public UserDto authenticate(CredentialsDto credentialsDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        credentialsDto.login(), credentialsDto.password()
                )
        );
        User user = userRepository.findByUsernameOrContact(credentialsDto.login()).orElseThrow(() -> new AppException("User not found.",HttpStatus.FORBIDDEN));
        String jwtToken = JwtService.generateToken(user);
        UserDto userDto = userMapper.toUserDto(user); 
        System.out.println(userDto);
        userDto.setToken(jwtToken);
        return userDto;
    }

    @Override
    public User findById(Long userId) {
       return(userRepository.findById(userId).orElseThrow(() -> new AppException("User not found", HttpStatus.NOT_FOUND)));
    }

    @Override
    public ProfileResponseDto fetchProfileByID(Long userID) {
        User user = userRepository.findById(userID).orElseThrow(()-> new AppException("User not found", HttpStatus.NOT_FOUND));
        ProfileResponseDto responseDto = userMapper.toProfileResponseDto(user);
        HashMap<String, List<Long>> followerCount = followService.fetchFollowerAndFollowingIDs(userID);
        responseDto.setFollowerCount(followerCount.get("followers").size());
        responseDto.setFollowingCount(followerCount.get("following").size());
        return(responseDto);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T saveProfile(ProfileRequestDto profileRequestDto) {
        User user = userRepository.findById(profileRequestDto.getId()).orElseThrow(()-> new AppException("User not found", HttpStatus.NOT_FOUND));
        boolean isNewUsername = false;
        boolean isNewContact = false;
        if(user != null){
            user.setBio(profileRequestDto.getBio());
            user.setFullname(profileRequestDto.getFullname());
            user.setLink(profileRequestDto.getLink());
            user.setSex(profileRequestDto.getSex());
            if(profileRequestDto.getPassword() != null){
                user.setPassword(PasswordEncoderConfig.getInstance().encode(CharBuffer.wrap(profileRequestDto.getPassword())));
            }
            if(!user.getUsername().equals(profileRequestDto.getUsername())){
                if(userRepository.findByUsername(profileRequestDto.getUsername()).isEmpty()){
                    user.setUsername(profileRequestDto.getUsername());
                    isNewUsername = true;
                }
                else{
                    throw new AppException("Username taken " + user.getUsername(), HttpStatus.CONFLICT);
                }
            }
            if(!user.getContact().equals(profileRequestDto.getContact())){
                if(userRepository.findByUsernameOrContact(profileRequestDto.getContact()).isEmpty()){
                    user.setContact(profileRequestDto.getContact());
                    isNewContact = true;
                }
                else{
                    throw new AppException("Contact already exists", HttpStatus.CONFLICT);
                }
            }
        }
        ProfileResponseDto responseDto = userMapper.toProfileResponseDto(userRepository.save(user));
        if (isNewUsername && isNewContact) {
            UserDto userDto = userMapper.toUserDto(user);
            userDto.setToken(JwtService.generateToken(user));
            ProfileResponseWithUserDto responseWithUserDto = profileMapper.toProfileResponseWithUserDto(responseDto);
            responseWithUserDto.setUserDto(userDto);
            return (T) responseWithUserDto;
        } else {
            return (T) responseDto;
        }
    }

    @Override
    public ProfilePageResponseDto fetchFullProfile(Long userID) {
        ProfilePageResponseDto profilePageResponseDto = profileMapper.tProfilePageResponseDto(fetchProfileByID(userID));
        HashMap<String,List<Long>> followMap = followService.fetchFollowerAndFollowingIDs(userID);
        profilePageResponseDto.setFollowers(followMap.get("followers").stream()
                                               .map(this :: fetchProfileByID)
                                               .collect(Collectors.toList())
                                                );
        profilePageResponseDto.setFollowing(followMap.get("following").stream()
                                               .map(this :: fetchProfileByID)
                                               .collect(Collectors.toList())
                                                );                                        
        return(profilePageResponseDto);
    }

    @Override
    public ProfileResponseDto updateProfilePicture(MultipartFile file, Long UserID) {
        User user = userRepository.findById(UserID).get();
        user.setProfileImage(MediaStorageUtil.saveImage(file));
        return(userMapper.toProfileResponseDto(userRepository.save(user)));
    }

    
}