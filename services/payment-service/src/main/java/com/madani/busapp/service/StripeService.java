package com.madani.busapp.service;

import com.madani.busapp.dto.ProductRequest;
import com.madani.busapp.dto.StripeResponse;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class StripeService {
    /*
     * Stripe API Integration
     * This service handles the creation of a checkout session for a given product.
     * Inputs: Product details (name, amount, quantity, currency).
     * Outputs: A response containing the session ID and the session URL.
     */

    @Value("${stripe.api.secretKey}")
    private String secretKey;
    @Value("${application.config.success-url}")
    private String successUrl;
    @Value("${application.config.cancel-url}")
    private String cancelUrl;

    public StripeResponse checkoutProducts(ProductRequest productRequest) {
        /* Set your secret key for Stripe.
         * Make sure to use the live secret key in production. */
        Stripe.apiKey = secretKey;

        /* Create product data for the checkout session. */
        SessionCreateParams.LineItem.PriceData.ProductData productData =
                SessionCreateParams.LineItem.PriceData.ProductData.builder()
                        .setName(productRequest.getProductName())
                        .build();

        /* Define pricing details for the product, including currency and amount. */
        SessionCreateParams.LineItem.PriceData priceData =
                SessionCreateParams.LineItem.PriceData.builder()
                        .setCurrency(productRequest.getCurrency() != null ? productRequest.getCurrency() : "USD")
                        .setUnitAmount(productRequest.getAmount())
                        .setProductData(productData)
                        .build();

        /* Create a line item with the price data and product quantity. */
        SessionCreateParams.LineItem lineItem =
                SessionCreateParams
                        .LineItem.builder()
                        .setQuantity(productRequest.getQuantity())
                        .setPriceData(priceData)
                        .build();

        /* Build the checkout session parameters, including success and cancel URLs. */
        SessionCreateParams params =
                SessionCreateParams.builder()
                        .setMode(SessionCreateParams.Mode.PAYMENT)
                        .setSuccessUrl("http://localhost:8090/success")
                        .setCancelUrl("http://localhost:8090/cancel")
                        .addLineItem(lineItem)
                        .build();

        /* Create a new session using the Stripe API. */
        Session session = null;
        try {
            session = Session.create(params);
        } catch (StripeException e) {
            /* Log the error if session creation fails. */
            System.out.println(e.getMessage());
        }

        /* Return the response containing the session details. */
        return StripeResponse
                .builder()
                .status("SUCCESS")
                .message("Payment session created")
                .sessionId(session.getId())
                .sessionUrl(session.getUrl())
                .build();
    }
}