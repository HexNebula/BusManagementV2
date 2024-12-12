package com.example.notificationservice.config;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class NotificationProducer {

    private final RabbitTemplate rabbitTemplate;

    @Value("${spring.rabbitmq.exchange.notification}")
    private String notificationExchange;

    public NotificationProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendTicketPurchasedEvent(String ticketDetails) {
        rabbitTemplate.convertAndSend(notificationExchange, "ticket.purchased", ticketDetails);
        System.out.println("Ticket purchased event sent: " + ticketDetails);
    }

    public void sendReservationConfirmedEvent(String reservationDetails) {
        rabbitTemplate.convertAndSend(notificationExchange, "reservation.confirmed", reservationDetails);
        System.out.println("Reservation confirmed event sent: " + reservationDetails);
    }
}
