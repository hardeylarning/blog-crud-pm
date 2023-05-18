package com.roq.blogcrud.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class LoginRequest {
    @Email(message = "must be a valid email")
    private String email;
    @NotBlank(message = "password is required")
    private String password;
}
