package com.microservices.businformationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusInformationDto {
    private Long id;
    private String busNumber;
    private int capacity;
    private String busType;
    private String serviceProvider;
    private CityDto departureCity; // DTO for the departure city
    private CityDto arrivalCity;  // DTO for the arrival city
    private Set<TrajetDto> trajets;  // Added Set of TrajetDto
}