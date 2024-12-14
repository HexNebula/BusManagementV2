package com.microservices.busapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TrajetDto {
    private Long id;            // Unique identifier for the trajectory
    private CityDto depart;     // Departure city as CityDto
    private CityDto arrivee;    // Arrival city as CityDto
    private List<ArretDto> stops;
}