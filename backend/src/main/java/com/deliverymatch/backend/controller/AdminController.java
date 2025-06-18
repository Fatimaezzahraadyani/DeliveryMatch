package com.deliverymatch.backend.controller;


import com.deliverymatch.backend.dto.CreateUserDto;
import com.deliverymatch.backend.dto.UpdateUserDto;
import com.deliverymatch.backend.dto.UserDTO;
import com.deliverymatch.backend.model.User;
import com.deliverymatch.backend.services.UserServices;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/users")
public class AdminController {

    private final UserServices userServices;

    public AdminController(UserServices userServices) {
        this.userServices = userServices;
    }


    @GetMapping
    public List<UserDTO> getAllUsers() {
        return userServices.getAllUsers();
    }

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody @Valid CreateUserDto dto) {
        return userServices.createUserByAdmin(dto);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody @Valid UpdateUserDto dto) {
        User updateUser = userServices.UpdateUser(id, dto);
        return ResponseEntity.ok().body(updateUser);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable Long id) {
       userServices.deleteUser(id);
    }

}
