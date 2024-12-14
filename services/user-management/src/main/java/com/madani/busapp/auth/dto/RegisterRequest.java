package com.madani.busapp.auth.dto;

public record RegisterRequest(
        String firstName,
        String lastName,
        String email,
        String password
) {
}
