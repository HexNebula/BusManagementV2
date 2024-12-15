package com.madani.busapp.kafka.payment;

import java.math.BigDecimal;

public record PaymentConfirmation(
        String orderReference,
        PaymentMethod paymentMethod,
        BigDecimal amount,
        String id,
        String customerFirstName,
        String customerLastName,
        String customerEmail
) {
}
