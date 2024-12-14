package com.madani.busapp.auth.dto;

public record AuthenticationRequest(
        String email,
        String password
) {
}
