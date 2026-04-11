package com.manhcia.api_vet_clinic.services;

import com.manhcia.api_vet_clinic.dtos.request.UserRequest;
import com.manhcia.api_vet_clinic.dtos.response.UserResponse;
import com.manhcia.api_vet_clinic.models.User;
import com.manhcia.api_vet_clinic.repositories.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.stream.Collectors;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Page<UserResponse> getUsers(Pageable pageable) {
        return userRepository.findAll(pageable)
                .map(user -> new UserResponse(user));
    }

    public UserResponse findUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        return new UserResponse(user);
    }

    public UserResponse create(UserRequest dto) {
        User user = new User();
        copyDtoToEntity(dto, user);
        return new UserResponse(userRepository.save(user));
    }

    public UserResponse update(Long id, UserRequest dto){
        User user  = userRepository.getReferenceById(id);
        copyDtoToEntityOnUpdate(dto, user);
        return new UserResponse(userRepository.save(user));
    }

    public void deleteUserById(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found with id: " + id);
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
