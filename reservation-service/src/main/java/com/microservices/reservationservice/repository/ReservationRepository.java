package com.microservices.reservationservice.repository;

import com.microservices.reservationservice.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
}
