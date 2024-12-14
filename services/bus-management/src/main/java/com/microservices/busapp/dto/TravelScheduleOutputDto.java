package com.microservices.busapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TravelScheduleOutputDto {
    private Long id;                     // Unique identifier for the travel schedule
    private String scheduleName;         // Name or identifier for the schedule
    private BusInformationDto bus;       // Details about the associated bus
    private LocalTime departureTime;     // Scheduled departure time
    private LocalTime arrivalTime;       // Scheduled arrival time
    private LocalDate departureDate;    // Date of scheduled departure
    private LocalDate arrivalDate;      // Date of scheduled arrival
    private CityDto departureCity;      // City where the schedule starts
    private CityDto arrivalCity;        // City where the schedule ends
}
