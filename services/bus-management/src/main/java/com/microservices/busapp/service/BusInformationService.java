package com.microservices.busapp.service;

import com.microservices.busapp.dto.BusInformationDto;

import java.util.List;

public interface BusInformationService {
    BusInformationDto addTravel(BusInformationDto informationDto);
    BusInformationDto getTravelById(Long id);
    List<BusInformationDto> getAllTravels();
    BusInformationDto updateTravel(Long id, BusInformationDto informationDto);
    void deleteTravel(Long id);
}
