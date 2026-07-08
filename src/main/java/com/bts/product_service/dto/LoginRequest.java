package com.bts.product_service.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginRequest {
    @NotEmpty(message = "this field cant be empty")
    @NotNull(message = "this field cant be null")
    private String username;

    @NotEmpty(message = "this field cant be empty")
    @NotNull(message = "this field cant be null")
    private String password;
}
