package com.microservices.busapp.service;

import com.microservices.busapp.dto.TravelScheduleInputDto;
import com.microservices.busapp.dto.TravelScheduleOutputDto;

import java.util.List;

public interface TravelScheduleService {
    TravelScheduleOutputDto addTravelSchedule(TravelScheduleInputDto inputDto);
    TravelScheduleOutputDto getTravelScheduleById(Long id);
    List<TravelScheduleOutputDto> getAllTravelSchedules();
    TravelScheduleOutputDto updateTravelSchedule(Long id , TravelScheduleInputDto inputDto);
    void deleteTravelSchedule(Long id);
    List<TravelScheduleOutputDto> getAllTravelSchedulesByTravelId(Long travelId);
}
