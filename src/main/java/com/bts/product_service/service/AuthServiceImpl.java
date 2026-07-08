package com.bts.product_service.service;

import com.bts.product_service.dto.CommonResponse;
import com.bts.product_service.dto.LoginRequest;
import com.bts.product_service.dto.RegisterRequestDTO;
import com.bts.product_service.model.User;
import com.bts.product_service.repository.UserRepository;
import com.bts.product_service.utils.JwtUtil;
import com.bts.product_service.utils.ResponseUtil;
import com.nimbusds.jose.JOSEException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtService;

    @Override
    public ResponseEntity<CommonResponse> register(RegisterRequestDTO request) {

        if (userRepository.existsByUsername(request.getUsername())) {
           return ResponseUtil.failed("Username is already in use");
        }

        User newUser = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        userRepository.save(newUser);

        return ResponseUtil.success("Successfully registered", newUser);
    }

    @Override
    public ResponseEntity<CommonResponse> login(LoginRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );

            String accessToken = jwtService.generateToken(request.getUsername());

            return ResponseUtil.success("Successfully logged in", accessToken);

        } catch (AuthenticationException e) {
            return ResponseUtil.failedWithStatus(HttpStatus.UNAUTHORIZED, e.getMessage());

        } catch (JOSEException e) {
            return ResponseUtil.failed("Failed to generate JWT Token");
        }
    }
}
