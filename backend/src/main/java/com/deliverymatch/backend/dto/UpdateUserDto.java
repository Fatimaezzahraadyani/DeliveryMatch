package com.deliverymatch.backend.dto;

import com.deliverymatch.backend.model.Role;

public record UpdateUserDto(
        String firstName,
        String lastName,
        String email,
        String password,
        Role role

) {
}
