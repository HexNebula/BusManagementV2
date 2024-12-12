package com.example.notificationservice.controller;


import com.example.notificationservice.config.NotificationProducer;
import com.example.notificationservice.config.NotificationProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationProducer notifProducer;

    public NotificationController(NotificationProducer notifProducer) {
        this.notifProducer = notifProducer;
    }

    @PostMapping("/ticket-purchased")
    public ResponseEntity<String> ticketPurchased(@RequestBody String ticketDetails) {
        notifProducer.sendTicketPurchasedEvent(ticketDetails);
        return ResponseEntity.ok("Ticket purchased event sent.");
    }

    @PostMapping("/reservation-confirmed")
    public ResponseEntity<String> reservationConfirmed(@RequestBody String reservationDetails) {
        notifProducer.sendReservationConfirmedEvent(reservationDetails);
        return ResponseEntity.ok("Reservation confirmed event sent.");
    }
}
