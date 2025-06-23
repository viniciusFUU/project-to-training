package com.mon_gs.email.E_mail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender emailSender;

    public void sendEmail(String to, String user){
        SimpleMailMessage message = new SimpleMailMessage();
    
        message.setTo(to);
        message.setSubject("Created an account Mon_gs");
        message.setText("Welcome to Mon_gs! Your account has been created!\nYour user is: " + user);
        message.setFrom("viniciusvasmonteiro@gmail.com");

        emailSender.send(message);
    }
}
