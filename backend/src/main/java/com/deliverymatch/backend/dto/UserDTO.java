package com.deliverymatch.backend.dto;

public record UserDTO(
        long id,
        String firstName,
        String lastName,
        String email,
        String role
) {


}
