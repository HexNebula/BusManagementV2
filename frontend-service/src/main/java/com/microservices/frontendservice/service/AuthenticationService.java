package com.microservices.frontendservice.service;

import com.microservices.frontendservice.auth.AuthenticationRequest;
import com.microservices.frontendservice.auth.AuthenticationResponse;
import com.microservices.frontendservice.auth.RegisterRequest;
import com.microservices.frontendservice.config.JwtService;
import com.microservices.frontendservice.model.Role;
import com.microservices.frontendservice.model.UserAuth;
import com.microservices.frontendservice.repository.UserAuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserAuthRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = UserAuth.builder().userName(request.getUserName())
                .password(passwordEncoder.encode(request.getPassword())).role(Role.USER).build();
        repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword())
        );
        var user = repository.findByUserName(request.getUserName()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }
}
