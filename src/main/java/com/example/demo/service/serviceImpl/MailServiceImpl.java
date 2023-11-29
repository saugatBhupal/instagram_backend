package com.example.demo.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.demo.service.MailService;

@Service
public class MailServiceImpl implements MailService{
    @Autowired
    private JavaMailSender mailSender;

    public void sendMail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setFrom("Instagram@sbhupal.com.np");
        message.setSubject(subject);
        message.setText(body);
        System.out.println(message.getText());
        mailSender.send(message);
    }
}