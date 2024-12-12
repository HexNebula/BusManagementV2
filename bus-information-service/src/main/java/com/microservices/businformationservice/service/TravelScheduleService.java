package com.microservices.businformationservice.service;

import com.microservices.businformationservice.dto.TravelScheduleInputDto;
import com.microservices.businformationservice.dto.TravelScheduleOutputDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TravelScheduleService {
    TravelScheduleOutputDto addTravelSchedule(TravelScheduleInputDto inputDto);
    TravelScheduleOutputDto getTravelScheduleById(Long id);
    List<TravelScheduleOutputDto> getAllTravelSchedules();
    TravelScheduleOutputDto updateTravelSchedule(Long id , TravelScheduleInputDto inputDto);
    void deleteTravelSchedule(Long id);
    List<TravelScheduleOutputDto> getAllTravelSchedulesByTravelId(Long travelId);
}
