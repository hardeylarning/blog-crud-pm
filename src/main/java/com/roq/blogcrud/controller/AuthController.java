package com.roq.blogcrud.controller;

import com.roq.blogcrud.dto.JwtResponse;
import com.roq.blogcrud.dto.LoginRequest;
import com.roq.blogcrud.dto.RegisterRequest;
import com.roq.blogcrud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<JwtResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(userService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<JwtResponse> authenticate(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(userService.authenticate(request));
    }
}
