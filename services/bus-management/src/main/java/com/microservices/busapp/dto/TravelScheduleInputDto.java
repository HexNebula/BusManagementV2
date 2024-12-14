package com.microservices.busapp.dto;

import com.microservices.busapp.model.City;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TravelScheduleInputDto {
    private Long id;
    private String scheduleName;        // Schedule name
    private LocalDate departureDate;    // Departure date
    private LocalDate arrivalDate;      // Arrival date
    private LocalTime departureTime;    // Departure time
    private LocalTime arrivalTime;      // Arrival time
    private City departureCity;       // City where the bus departs from
    private City arrivalCity;         // City where the bus arrives at
    private Long busInformationId;      // The ID of the bus information related to this schedule
}
