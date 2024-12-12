package com.microservices.businformationservice.mapper;

import com.microservices.businformationservice.dto.CityDto;
import com.microservices.businformationservice.dto.TrajetDto;
import com.microservices.businformationservice.model.City;
import com.microservices.businformationservice.model.Trajet;
import com.microservices.businformationservice.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@RequiredArgsConstructor
@Component
public class TrajetMapper {
    private final CityRepository cityRepository;
    public TrajetDto mapToTrajetDto(Trajet trajet) {
        // Mapping depart and arrivee from City to CityDto
        CityDto departCityDto = new CityDto(
                trajet.getDepart().getId(),
                trajet.getDepart().getName(),
                trajet.getDepart().getCountry()
        );

        CityDto arriveeCityDto = new CityDto(
                trajet.getArrivee().getId(),
                trajet.getArrivee().getName(),
                trajet.getArrivee().getCountry()
        );

        return new TrajetDto(
                trajet.getId(),
                departCityDto,
                arriveeCityDto
        );
    }

    public Trajet mapToTrajet(TrajetDto trajetDto) {
        // Fetch the City entities from the CityRepository using the IDs in the CityDto
        City departCity = cityRepository.findById(trajetDto.getDepart().getId())
                .orElseThrow(() -> new RuntimeException("Departure city not found"));

        City arriveeCity = cityRepository.findById(trajetDto.getArrivee().getId())
                .orElseThrow(() -> new RuntimeException("Arrival city not found"));

        // Map the TrajetDto to Trajet entity
        return new Trajet(
                trajetDto.getId(),
                departCity,
                arriveeCity,
                new HashSet<>() // Assuming no buses are set initially, can be updated later
        );
    }
}