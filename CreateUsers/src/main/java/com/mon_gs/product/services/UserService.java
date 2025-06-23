package com.mon_gs.product.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.mon_gs.product.dto.EmailDto;
import com.mon_gs.product.entity.User;
import com.mon_gs.product.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final RestTemplate restTemplate = new RestTemplate();

    public String createUser(User user) {
        try{
            User createUser = new User();

            String criptografedPassword = passwordEncoder.encode(user.getPassword());
            createUser.setUserDescription(user.getUserDescription());
            createUser.setEmail(user.getEmail());
            createUser.setPassword(criptografedPassword);
            
            userRepository.save(createUser);

            String emailServiceUrl = "http://localhost:8082/email";

            EmailDto emailDto = new EmailDto();
            emailDto.setTo(createUser.getEmail());
            emailDto.setUser(createUser.getUserDescription());

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<EmailDto> request = new HttpEntity<>(emailDto, headers);

            restTemplate.postForObject(emailServiceUrl, request, String.class);

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
