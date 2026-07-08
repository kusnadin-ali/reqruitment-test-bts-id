package com.bts.product_service.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequestDTO {
    @NotEmpty(message = "this field cant be empty")
    @NotNull(message = "this field cant be null")
    private String username;

    @NotEmpty(message = "this field cant be empty")
    @NotNull(message = "this field cant be null")
    private String password;

    @NotEmpty(message = "this field cant be empty")
    @NotNull(message = "this field cant be null")
    private String password_confirmation;
}
