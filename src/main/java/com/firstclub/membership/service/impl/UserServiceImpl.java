package com.firstclub.membership.service.impl;

import com.firstclub.membership.data.dto.UserRequest;
import com.firstclub.membership.data.dto.UserResponse;
import com.firstclub.membership.data.entity.User;
import com.firstclub.membership.repository.UserRepository;
import com.firstclub.membership.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public ResponseEntity<UserResponse> createUser(UserRequest request) {
        User user = modelMapper.map(request, User.class);
        return ResponseEntity.ok(modelMapper.map(userRepository.save(user), UserResponse.class));
    }
    @Override
    public ResponseEntity<UserResponse> getByEmail(String email) {
        return ResponseEntity.ok(userRepository.findByEmail(email).map(user -> modelMapper.map(user, UserResponse.class)).orElseThrow(()->new NoSuchElementException()));
    }
    @Override
    public ResponseEntity<List<UserResponse>> getAll() {
        return ResponseEntity.ok(userRepository.findAll()
                .stream()
                .map(user -> modelMapper.map(user, UserResponse.class))
                .toList());
    }
}
