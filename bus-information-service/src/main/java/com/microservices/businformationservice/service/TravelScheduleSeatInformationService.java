package com.microservices.businformationservice.service;

import com.microservices.businformationservice.dto.TravelScheduleSeatInformationInputDto;
import com.microservices.businformationservice.dto.TravelScheduleSeatInformationOutputDto;

import java.util.List;

public interface TravelScheduleSeatInformationService {
    TravelScheduleSeatInformationOutputDto addSeatInformation(TravelScheduleSeatInformationInputDto inputDto);
    TravelScheduleSeatInformationOutputDto getSeatInformationById(Long id);
    List<TravelScheduleSeatInformationOutputDto> getAllSeatInformation();
    TravelScheduleSeatInformationOutputDto updateSeatInformation(Long id , TravelScheduleSeatInformationInputDto inputDto);
    void deleteSeatInformation(Long id);
    List<TravelScheduleSeatInformationOutputDto> getSeatInformationByTravelScheduleId(Long travelScheduleId);
    List<TravelScheduleSeatInformationOutputDto> getSeatInformationByBookingStatus(Boolean seatAvailability);
}
