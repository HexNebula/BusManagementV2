package com.example.notificationservice.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    // New properties
    @Value("${spring.rabbitmq.queue.ticketPurchased}")
    private String ticketPurchasedQueue;

    @Value("${spring.rabbitmq.queue.reservationConfirmed}")
    private String reservationConfirmedQueue;

    @Value("${spring.rabbitmq.exchange.notification}")
    private String notificationExchange;

    // Define new queues
    @Bean
    Queue ticketPurchasedQueue() {
        return new Queue(ticketPurchasedQueue, true);
    }

    @Bean
    Queue reservationConfirmedQueue() {
        return new Queue(reservationConfirmedQueue, true);
    }

    // Define new exchange
    @Bean
    Exchange notificationExchange() {
        return ExchangeBuilder.topicExchange(notificationExchange).durable(true).build();
    }

    // Bindings for new queues
    @Bean
    public Declarables notificationBindings() {
        return new Declarables(
                BindingBuilder.bind(ticketPurchasedQueue())
                        .to(notificationExchange())
                        .with("ticket.purchased")
                        .noargs(),
                BindingBuilder.bind(reservationConfirmedQueue())
                        .to(notificationExchange())
                        .with("reservation.confirmed")
                        .noargs()
        );
    }
}
