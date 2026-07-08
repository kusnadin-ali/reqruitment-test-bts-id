package com.bts.product_service.controller;

import com.bts.product_service.dto.CommonResponse;
import com.bts.product_service.dto.LoginRequest;
import com.bts.product_service.dto.RegisterRequestDTO;
import com.bts.product_service.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<CommonResponse> register(@RequestBody @Valid RegisterRequestDTO request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    public ResponseEntity<CommonResponse> login(@RequestBody @Valid LoginRequest request) {
        return authService.login(request);
    }
}
