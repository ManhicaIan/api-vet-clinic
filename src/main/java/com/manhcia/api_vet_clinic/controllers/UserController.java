package com.manhcia.api_vet_clinic.controllers;

import com.manhcia.api_vet_clinic.dtos.request.UserRequest;
import com.manhcia.api_vet_clinic.dtos.response.UserResponse;
import com.manhcia.api_vet_clinic.services.UserService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private UserService userService;

    public UserController ( UserService userService ) {
        this.userService = userService;
    }

    @GetMapping
    public Page<UserResponse> getUsers(Pageable pageable) {
        return userService.getUsers(pageable);
    }

    @GetMapping("/{id}")
    public UserResponse findUserById(@PathVariable Long id) {
        return userService.findUserById(id);
    }

    @PostMapping
    public UserResponse create(@RequestBody UserRequest dto) {
        return userService.create(dto);
    }

    @PutMapping("/{id}")
    public UserResponse update(@PathVariable Long id, @RequestBody UserRequest dto) {
        return userService.update(id, dto);
    }
}
