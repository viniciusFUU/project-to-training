package com.mon_gs.product.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mon_gs.product.entity.User;
import com.mon_gs.product.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String createUser(User user) {
        try{
            User createUser = new User();

            String criptografedPassword = passwordEncoder.encode(user.getPassword());
            createUser.setUserDescription(user.getUserDescription());
            createUser.setEmail(user.getEmail());
            createUser.setPassword(criptografedPassword);
            
            userRepository.save(createUser);
            return "User created successfully with ID: " + createUser.getId();
        } catch (Exception e) {
            return "Error creating user: " + e.getMessage();
        }
    }

    public String login(String email, String password) {
        boolean isValid = false;

        try {
            User user = userRepository.findByEmail(email);

            if(user == null){
                return "Usernot found: " + email;
            }

            if(passwordEncoder.matches(password, user.getPassword())){
                isValid = true;
            }

            if(!isValid){
                return "Invalid password for user: " + email;
            }

            return "Login successful for user: " + email;
        } catch (Exception e) {
            return "Error during login: " + e.getMessage();
        }

    }
}
