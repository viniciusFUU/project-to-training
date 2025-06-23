package com.mon_gs.email.E_mail.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mon_gs.email.E_mail.Dto.EmailDto;
import com.mon_gs.email.E_mail.service.EmailService;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/email")
public class EmailController {
    @Autowired
    private EmailService emailService;

    @PostMapping    
    public void sendEmail(@RequestBody EmailDto emailDto){
        emailService.sendEmail(emailDto.getTo(), emailDto.getUser());
    }
}
