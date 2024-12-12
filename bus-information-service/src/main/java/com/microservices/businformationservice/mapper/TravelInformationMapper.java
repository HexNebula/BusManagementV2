package com.microservices.businformationservice.mapper;

import com.microservices.businformationservice.dto.BusInformationDto;
import com.microservices.businformationservice.dto.CityDto;
import com.microservices.businformationservice.dto.TrajetDto;
import com.microservices.businformationservice.model.BusInformation;
import com.microservices.businformationservice.model.City;
import com.microservices.businformationservice.model.Trajet;
import com.microservices.businformationservice.repository.CityRepository;
import com.microservices.businformationservice.repository.TrajetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
@Component
@RequiredArgsConstructor
public class TravelInformationMapper {
    public final TrajetRepository trajetRepository;
    public final CityRepository cityRepository;
    public final CityMapper cityMapper;

    public TrajetDto mapToTrajetDto(Trajet trajet) {
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

        // Return a new TrajetDto with CityDto for depart and arrivee
        return new TrajetDto(
                trajet.getId(),
                departCityDto,
                arriveeCityDto
        );
    }

    public BusInformation mapToTravelInformation(BusInformationDto busInformationDto){
        City departureCity = cityRepository.findById(busInformationDto.getDepartureCity().getId())
                .orElseThrow(()-> new RuntimeException("Departure city not found"));
        City arrivalCity = cityRepository.findById(busInformationDto.getArrivalCity().getId())
                .orElseThrow(()-> new RuntimeException("Arrival city not found"));
        return new BusInformation(
                busInformationDto.getId(),
                busInformationDto.getBusNumber(),
                busInformationDto.getCapacity(),
                busInformationDto.getBusType(),
                busInformationDto.getServiceProvider(),
                departureCity,
                arrivalCity,
                null
        );
    }
    public BusInformationDto mapToTravelInformationDto(BusInformation busInformation){
        return new BusInformationDto(
                busInformation.getId(),
                busInformation.getBusNumber(),
                busInformation.getCapacity(),
                busInformation.getBusType(),
                busInformation.getServiceProvider(),
                cityMapper.mapToCityDto(busInformation.getDepartureCity()), // Map departure city
                cityMapper.mapToCityDto(busInformation.getArrivalCity()),    // Map arrival city
                null
        );
    }
}
