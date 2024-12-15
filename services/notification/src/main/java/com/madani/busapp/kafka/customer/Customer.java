package com.madani.busapp.kafka.customer;

public record Customer(
        String id,
        String firstName,
        String lastName,
        String email
) {
}
