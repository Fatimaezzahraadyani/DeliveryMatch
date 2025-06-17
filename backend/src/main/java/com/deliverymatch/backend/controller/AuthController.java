package com.deliverymatch.backend.controller;


import com.deliverymatch.backend.dto.LoginDTO;
import com.deliverymatch.backend.dto.RegisterDTO;
import com.deliverymatch.backend.model.User;
import com.deliverymatch.backend.services.UserServices;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")

public class AuthController {
    private final UserServices userService;

    public AuthController(UserServices userServices) {
        this.userService = userServices;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterDTO registerDTO) {
        return userService.register(registerDTO);
    }


    @PostMapping("/login")
    public User login(@RequestBody User user) {

        return null;
    }





}
