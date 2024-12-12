package com.microservices.reservationviewerservice.repository;

import com.microservices.reservationviewerservice.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//public interface ReservationViewerRepository extends JpaRepository<ReservationViewer, Integer> {
public interface ReservationViewerRepository extends JpaRepository<Reservation, Integer> {

    List<Reservation> findByCustomerId(Integer customerId);
    List<Reservation> findByFlightId(Integer flightId);
}
