package com.manhcia.api_vet_clinic.dtos.errors.validation;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FieldError {
    private String field;
    private String message;

}
