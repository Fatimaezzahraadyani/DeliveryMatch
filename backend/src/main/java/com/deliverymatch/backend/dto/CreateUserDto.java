package com.deliverymatch.backend.dto;

import com.deliverymatch.backend.model.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


//pour recevoir des données depuis le frontend
public record CreateUserDto(

        @NotBlank String firstName,
        @NotBlank String lastName,
        @NotBlank @Email String email,
        @NotBlank String password,
        @NotNull Role role

) {
}
