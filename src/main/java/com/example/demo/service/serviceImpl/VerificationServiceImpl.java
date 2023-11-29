package com.example.demo.service.serviceImpl;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.demo.dto.VerificationDto.VerificationRequestDto;
import com.example.demo.entities.User;
import com.example.demo.entities.Verification;
import com.example.demo.exceptions.AppException;
import com.example.demo.repository.VerificationRepository;
import com.example.demo.service.MailService;
import com.example.demo.service.UserService;
import com.example.demo.service.VerificationService;
import com.example.demo.utils.dateTimeUtil.DateTimeUtil;
import com.example.demo.utils.generatorUtil.VerificationTokenGenerator;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class VerificationServiceImpl implements VerificationService{
    private final VerificationRepository repository;
    private final MailService mailService;
    private final CustomUserDetailService customUserDetailService; // Avoid Circular Dependency

    @Override
    @Transactional
    public void Verify(VerificationRequestDto verificationRequestDto) {
        Verification verification = repository.findByUser(verificationRequestDto.getUser()).get();
        if(verification.getToken().equals(verificationRequestDto.getToken())) {
            repository.updateVerification(verificationRequestDto.getUser().getId());
            User user = customUserDetailService.findById(verificationRequestDto.getUser().getId());
            mailService.sendMail(
                            user.getContact(),
                            "Verify Your Instagram Account - Your Verification Code Inside",
                            "Hello " + user.getFullname() + ",\n\n" +
                            "Welcome to Instagram! We're thrilled to have you on board. To ensure the security of your account, we need to verify your email address. Please use the following verification code:\n\n" +
                            "Verification Code: [Your Verification Code]\n\n" +
                            "Simply enter this code on the verification page to complete the process.\n\n" +
                            "[Verify Now Button]\n\n" +
                            "If you didn't sign up for Instagram or have any concerns about this email, please let us know immediately. Your security is our top priority.\n\n" +
                            "Thanks for choosing Instagram. We can't wait for you to start sharing your moments with the world!\n\n" +
                            "Best,\n" +
                            "The Instagram Team"
            );
        }
        else{
            throw new AppException("Could Not Verify Email", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public void SendToken(User user) {
        Verification verification = new Verification();
        verification.setUser(user);
        verification.setVerified(false);
        verification.setToken(VerificationTokenGenerator.generate());
        repository.save(verification);
        mailService.sendMail(
                user.getContact(),
                "Verify Your Instagram Account - Your Verification Code Inside",
                "Hello " + user.getFullname() + ",\n\n" +
                "Welcome to Instagram! We're thrilled to have you on board. To ensure the security of your account, we need to verify your email address. Please use the following verification code:\n\n" +
                "Verification Code: "+ verification.getToken()+"\n\n" +
                "Simply enter this code on the verification page to complete the process.\n\n" +
                "If you didn't sign up for Instagram or have any concerns about this email, please let us know immediately. Your security is our top priority.\n\n" +
                "Thanks for choosing Instagram. We can't wait for you to start sharing your moments with the world!\n\n" +
                "Best,\n" +
                "The Instagram Team"
        );
        
    }
    
}
