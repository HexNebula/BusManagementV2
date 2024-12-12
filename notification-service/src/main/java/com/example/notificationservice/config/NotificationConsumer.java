package com.example.notificationservice.config;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class NotificationConsumer {

    @RabbitListener(queues = "${spring.rabbitmq.queue.ticketPurchased}")
    public void handleTicketPurchased(String message) {
        // Process ticket purchase notification
        System.out.println("Notification: Ticket purchased. Details: " + message);
        // Add notification logic (email, SMS, etc.)
    }

    @RabbitListener(queues = "${spring.rabbitmq.queue.reservationConfirmed}")
    public void handleReservationConfirmed(String message) {
        // Process reservation confirmation notification
        System.out.println("Notification: Reservation confirmed. Details: " + message);
        // Add notification logic (email, SMS, etc.)
    }
}
