package com.microservices.reservationservice.controller;

import com.microservices.reservationservice.dto.ReservationDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceResponse {
    private String message;
    private ReservationDTO reservationDTO;
}
