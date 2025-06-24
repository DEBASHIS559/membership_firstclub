package com.firstclub.membership.controller;

import com.firstclub.membership.data.dto.UserRequest;
import com.firstclub.membership.data.dto.UserResponse;
import com.firstclub.membership.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponse> create(@RequestBody UserRequest req) {
        return userService.createUser(req);
    }

    @GetMapping("/find")
    public ResponseEntity<UserResponse> getByEmail(@RequestParam String email) {
        return userService.getByEmail(email);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<UserResponse>> all() {
        return userService.getAll();
    }
}
