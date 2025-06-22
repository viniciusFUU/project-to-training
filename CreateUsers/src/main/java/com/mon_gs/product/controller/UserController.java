package com.mon_gs.product.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mon_gs.product.dto.LoginDto;
import com.mon_gs.product.entity.User;
import com.mon_gs.product.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

@RequestMapping("/users")
@RestController
public class UserController {
    @Autowired
    private UserService userService;
    
    @PostMapping
    public String createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping("/auth")
    public String authenticatioon(@RequestBody LoginDto loginDto) {
        return userService.login(loginDto.getEmail(), loginDto.getPassword());
    }
}
