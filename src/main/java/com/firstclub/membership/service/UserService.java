package com.firstclub.membership.service;

import com.firstclub.membership.data.dto.UserRequest;
import com.firstclub.membership.data.dto.UserResponse;
import com.firstclub.membership.data.entity.User;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {
    ResponseEntity<UserResponse> createUser(UserRequest request);
    ResponseEntity<UserResponse> getByEmail(String email);
    ResponseEntity<List<UserResponse>> getAll();
}