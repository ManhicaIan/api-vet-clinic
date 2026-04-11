package com.manhcia.api_vet_clinic.dtos.request;

import lombok.Getter;

public record UserRequest  (

        String name,
        String email,
        String phone,
        String password,
        String birthDate
) {
}
