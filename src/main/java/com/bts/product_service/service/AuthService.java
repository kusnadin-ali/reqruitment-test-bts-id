package com.bts.product_service.service;

import com.bts.product_service.dto.CommonResponse;
import com.bts.product_service.dto.LoginRequest;
import com.bts.product_service.dto.RegisterRequestDTO;
import org.springframework.http.ResponseEntity;

public interface AuthService {

    ResponseEntity<CommonResponse> register(RegisterRequestDTO request);

    ResponseEntity<CommonResponse> login(LoginRequest request);
}
