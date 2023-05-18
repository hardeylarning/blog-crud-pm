package com.roq.blogcrud.service;

import com.roq.blogcrud.dto.JwtResponse;
import com.roq.blogcrud.dto.LoginRequest;
import com.roq.blogcrud.dto.RegisterRequest;

public interface UserService {
    JwtResponse register(RegisterRequest registerRequest);

    JwtResponse authenticate(LoginRequest request);
}
