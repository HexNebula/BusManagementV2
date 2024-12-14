package com.madani.busapp.auth.service;

import com.madani.busapp.auth.dto.AuthenticationRequest;
import com.madani.busapp.auth.dto.AuthenticationResponse;
import com.madani.busapp.auth.dto.RegisterRequest;
import com.madani.busapp.config.JwtService;
import com.madani.busapp.user.Role;
import com.madani.busapp.user.model.User;
import com.madani.busapp.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .role(Role.USER)
                .build();
        repository.save(user);
        var jwtToken = jwtService.generateToke(user);
        return new AuthenticationResponse(jwtToken);
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );
        var user = repository.findByEmail(request.email())
                .orElseThrow(()->new UsernameNotFoundException("Username not found"));
        var jwtToken = jwtService.generateToke(user);
        return new AuthenticationResponse(jwtToken);
    }
}
