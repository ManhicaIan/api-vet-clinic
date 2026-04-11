package com.manhcia.api_vet_clinic.dtos.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

public record UserRequest  (

        @NotBlank(message = "Fill the name field")
        @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters")
        String name,

        @NotBlank(message = "Fill the email field")
        @Email(message = "Email should be valid")
        String email,

        @NotBlank(message = "Fill the phone field")
        @Size(min = 9, max = 9, message = "Phone number must be exactly 9 characters")
        String phone,

        @NotBlank(message = "Fill the password field")
        String password,

        @NotBlank(message = "Fill the birthDate field")
        String birthDate
) {
}
