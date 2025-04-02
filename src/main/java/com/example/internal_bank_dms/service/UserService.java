package com.example.internal_bank_dms.service;

import com.example.internal_bank_dms.dto.LoginDto;
import com.example.internal_bank_dms.entity.User;
import com.example.internal_bank_dms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<List<User>> getAllUsers()
    {
         return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<String> registerUser(User user)
    {
        String encodedPassword = Base64.getEncoder().encodeToString (user.getPassword().getBytes());
        user.setPassword(encodedPassword);
        System.out.println(user.getPassword());
        userRepository.save(user);
        return new ResponseEntity<>("User registered successfully", HttpStatus.CREATED);
    }

    public ResponseEntity<String> loginUser(LoginDto loginDto)
    {
        if(userRepository.findByEmail(loginDto.getEmail()))
        {
            return new ResponseEntity<>("user logged in successfully", HttpStatus.OK);
        }
        else return new ResponseEntity<>("user not found", HttpStatus.BAD_REQUEST);

    }

}
