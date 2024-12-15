package com.madani.busapp.kafka.order;

import com.madani.busapp.kafka.customer.Customer;
import com.madani.busapp.kafka.payment.PaymentMethod;
import com.madani.busapp.kafka.product.Product;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        Customer customer,
        List<Product> products
) {
}
