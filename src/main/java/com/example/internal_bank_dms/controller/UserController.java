package com.example.internal_bank_dms.controller;

import com.example.internal_bank_dms.dto.LoginDto;
import com.example.internal_bank_dms.entity.User;
import com.example.internal_bank_dms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "v1/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(path = "/allusers")
    public ResponseEntity<List<User>> getAllUsers()
    {
        return userService.getAllUsers();
    }

    @PostMapping(path = "/register")
    public ResponseEntity<String> registerUser(@RequestBody User user)
    {
         return userService.registerUser(user);

    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginDto loginDto)
    {
          return userService.loginUser(loginDto);
    }

}
