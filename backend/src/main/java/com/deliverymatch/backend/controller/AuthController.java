package com.deliverymatch.backend.controller;


import com.deliverymatch.backend.dto.AuthRequest;
import com.deliverymatch.backend.dto.AuthResponse;
import com.deliverymatch.backend.dto.LoginDTO;
import com.deliverymatch.backend.dto.RegisterDTO;
import com.deliverymatch.backend.model.User;
import com.deliverymatch.backend.services.AuthService;
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
    private final AuthService authService;

    public AuthController(UserServices userServices, AuthService authService) {
        this.userService = userServices;
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterDTO registerDTO) {
        return userService.register(registerDTO);
    }


    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request){

        String jwt = authService.login(request);
        return ResponseEntity.ok(new AuthResponse(jwt));
    }





}
