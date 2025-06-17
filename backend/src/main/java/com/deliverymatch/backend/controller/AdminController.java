package com.deliverymatch.backend.controller;


import com.deliverymatch.backend.dto.UserDTO;
import com.deliverymatch.backend.services.UserServices;
import org.apache.catalina.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
