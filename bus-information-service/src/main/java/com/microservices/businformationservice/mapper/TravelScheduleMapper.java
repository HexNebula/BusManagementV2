package com.microservices.businformationservice.mapper;

import com.microservices.businformationservice.dto.BusInformationDto;
import com.microservices.businformationservice.dto.CityDto;
import com.microservices.businformationservice.dto.TravelScheduleInputDto;
import com.microservices.businformationservice.dto.TravelScheduleOutputDto;
import com.microservices.businformationservice.model.BusInformation;
import com.microservices.businformationservice.model.City;
import com.microservices.businformationservice.model.TravelSchedule;
import com.microservices.businformationservice.repository.BusInformationRepository;
import com.microservices.businformationservice.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class TravelScheduleMapper {
    public final BusInformationRepository busRepository;
    public final CityRepository cityRepository;

    public TravelSchedule mapToTravelSchedule(TravelScheduleInputDto inputDto){
        City departureCity = cityRepository.findById(inputDto.getDepartureCity().getId())
                .orElseThrow(()->new RuntimeException("Departure city not found"));
        City arrivalCity = cityRepository.findById(inputDto.getArrivalCity().getId())
                .orElseThrow(()->new RuntimeException("Arrival city not found"));
        BusInformation bus = busRepository.findById(inputDto.getBusInformationId())
                .orElseThrow(()->new RuntimeException("Bus information not found"));

        LocalDateTime departureDateTime = LocalDateTime.of(inputDto.getDepartureDate(), inputDto.getDepartureTime());
        LocalDateTime arrivalDateTime = LocalDateTime.of(inputDto.getArrivalDate(), inputDto.getArrivalTime());

        // Map to TravelSchedule entity
        return new TravelSchedule(
                inputDto.getId(),
                bus,
                departureCity,
                arrivalCity,
                departureDateTime,
                arrivalDateTime
        );
    }

    public TravelScheduleOutputDto mapToTravelScheduleOutputDto(TravelSchedule travelSchedule) {
        return new TravelScheduleOutputDto(
                travelSchedule.getId(),
                travelSchedule.getScheduleName(),
                new BusInformationDto(
                        travelSchedule.getBus().getId(),
                        travelSchedule.getBus().getBusNumber(),
                        travelSchedule.getBus().getCapacity(),
                        travelSchedule.getBus().getBusType(),
                        travelSchedule.getBus().getServiceProvider(),
                        null, // Departure city info is already covered separately
                        null,
                        null // Arrival city info is already covered separately
                ),
                travelSchedule.getDepartureTime().toLocalTime(),
                travelSchedule.getArrivalTime().toLocalTime(),
                travelSchedule.getDepartureTime().toLocalDate(),
                travelSchedule.getArrivalTime().toLocalDate(),
                new CityDto(
                        travelSchedule.getDepartureCity().getId(),
                        travelSchedule.getDepartureCity().getName(),
                        travelSchedule.getDepartureCity().getCountry()
                ),
                new CityDto(
                        travelSchedule.getArrivalCity().getId(),
                        travelSchedule.getArrivalCity().getName(),
                        travelSchedule.getArrivalCity().getCountry()
                )
        );
    }
}
