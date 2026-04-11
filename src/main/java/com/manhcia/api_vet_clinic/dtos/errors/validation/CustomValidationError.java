package com.manhcia.api_vet_clinic.dtos.errors.validation;

import com.manhcia.api_vet_clinic.dtos.errors.CustomError;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Getter
public class CustomValidationError extends CustomError {
    private List<FieldError> fieldErrors = new ArrayList<>();

    public CustomValidationError(Instant timestamp, int status, String error, String path) {
        super(timestamp, status, error, path);
    }

    public void addFieldError(String field, String message) {
        this.fieldErrors.add(new FieldError(field, message));
    }
}
