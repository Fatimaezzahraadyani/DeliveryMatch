package com.deliverymatch.backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginDTO (

        @NotBlank(message = "email is required")
        @Email( message = "please enter a valid email")
        String email,

        @NotBlank( message = "password is required" )
        String password
){
}
