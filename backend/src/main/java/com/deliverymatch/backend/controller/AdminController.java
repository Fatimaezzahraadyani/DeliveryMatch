package com.deliverymatch.backend.controller;


import com.deliverymatch.backend.dto.CreateUserDto;
import com.deliverymatch.backend.dto.UserDTO;
import com.deliverymatch.backend.services.UserServices;
import jakarta.validation.Valid;
import org.apache.catalina.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final UserServices userServices;

    public AdminController(UserServices userServices) {
        this.userServices = userServices;
    }


    @GetMapping("/dashboard/users")
    public List<UserDTO> getAllUsers() {
        return userServices.getAllUsers();
    }

    @PostMapping("/dashboard/users/create")
    public ResponseEntity<?> createUser(@RequestBody @Valid CreateUserDto dto) {
        return userServices.createUserByAdmin(dto);
    }


}
