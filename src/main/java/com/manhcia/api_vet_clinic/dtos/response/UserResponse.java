package com.manhcia.api_vet_clinic.dtos.response;

import com.manhcia.api_vet_clinic.models.User;

import java.time.LocalDate;

public record UserResponse(
        Long id,
        String name,
        String email,
        String phone,
        LocalDate birthDate
) {
    public UserResponse(User user){
        this(user.getId(), user.getName(), user.getEmail(), user.getPhone(), user.getBirthDate());
    }
}
