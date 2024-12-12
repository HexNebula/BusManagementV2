package com.microservices.businformationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrajetDto {
    private Long id;            // Unique identifier for the trajectory
    private CityDto depart;     // Departure city as CityDto
    private CityDto arrivee;    // Arrival city as CityDto
}