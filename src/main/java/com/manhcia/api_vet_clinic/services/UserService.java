package com.manhcia.api_vet_clinic.services;

import com.manhcia.api_vet_clinic.dtos.request.UserRequest;
import com.manhcia.api_vet_clinic.dtos.response.UserResponse;
import com.manhcia.api_vet_clinic.models.User;
import com.manhcia.api_vet_clinic.repositories.UserRepository;
import com.manhcia.api_vet_clinic.services.exceptions.EmailRegisteredException;
import com.manhcia.api_vet_clinic.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.stream.Collectors;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public Page<UserResponse> getUsers(Pageable pageable) {
        return userRepository.findAll(pageable)
                .map(user -> new UserResponse(user));
    }

    @Transactional(readOnly = true)
    public UserResponse findUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return new UserResponse(user);
    }

    @Transactional
    public UserResponse create(UserRequest dto) {
        try {
            User user = new User();
            copyDtoToEntity(dto, user);
            return new UserResponse(userRepository.save(user));
        } catch (DataIntegrityViolationException e) {
            throw new EmailRegisteredException("Email already registered");
        }
    }

    @Transactional
    public UserResponse update(Long id, UserRequest dto){
        try {
            User user  = userRepository.getReferenceById(id);
            copyDtoToEntityOnUpdate(dto, user);
            return new UserResponse(userRepository.save(user));
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("User not found");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void deleteUserById(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("User not found");
        }
        userRepository.deleteById(id);
    }


    private void copyDtoToEntity(UserRequest dto, User user) {
        user.setName(dto.name());
        user.setEmail(dto.email());
        user.setPhone(dto.phone());
        user.setPassword(dto.password());
        user.setBirthDate(LocalDate.parse(dto.birthDate()));
    }

    private void copyDtoToEntityOnUpdate(UserRequest dto, User user) {
        if(dto.name() != null) {
            user.setName(dto.name());
        }
        if (dto.email() != null) {
            user.setEmail(dto.email());
        }
        if (dto.phone() != null) {
            user.setPhone(dto.phone());
        }
        if (dto.password() != null) {
            user.setPassword(dto.password());
        }

        if (dto.birthDate() != null) {
            user.setBirthDate(LocalDate.parse(dto.birthDate()));
        }
    }
}
