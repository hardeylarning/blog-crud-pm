package com.roq.blogcrud.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class RegisterRequest {
    @NotBlank(message = "firstname cannot be empty")
    @Size(min = 2, max = 20, message = "firstname must not less than 2 and greater than 20 character")
    private String firstName;
    @NotBlank(message = "lastname cannot be empty")
    @Size(min = 2, max = 20, message = "firstname must not less than 2 and greater than 20 character")
    private String lastName;

    @Email(message = "must be a valid email")
    private String email;

    @NotBlank(message = "password is required")
    @Size(min = 6, message = "password must not less than 6 characters")
    private String password;
}
