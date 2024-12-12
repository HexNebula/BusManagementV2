package com.microservices.businformationservice.controller;

import com.microservices.businformationservice.dto.TravelScheduleInputDto;
import com.microservices.businformationservice.dto.TravelScheduleOutputDto;
import com.microservices.businformationservice.service.TravelScheduleService;
import jakarta.ws.rs.Path;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bus_reservation/api/travels-schedules")
@RequiredArgsConstructor
public class TravelScheduleController {
    public final TravelScheduleService service;


    @RequestMapping
    public ResponseEntity<TravelScheduleOutputDto> createTravelSchedule(
            @RequestBody TravelScheduleInputDto travelScheduleInputDto
    ){
        TravelScheduleOutputDto createdSchedule = service.addTravelSchedule(travelScheduleInputDto);
        return ResponseEntity.ok(createdSchedule);
    }

    @GetMapping("/{schedule-id}")
    public ResponseEntity<TravelScheduleOutputDto> getTravelScheduleById(@PathVariable("schedule-id") Long scheduleId){
        TravelScheduleOutputDto travelScheduleOutputDto = service.getTravelScheduleById(scheduleId);
        return ResponseEntity.ok(travelScheduleOutputDto);
    }

    @GetMapping
    public ResponseEntity<List<TravelScheduleOutputDto>> getAllTravelSchedules() {
        List<TravelScheduleOutputDto> travelSchedules = service.getAllTravelSchedules();
        return ResponseEntity.ok(travelSchedules);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TravelScheduleOutputDto> updateFlightSchedule(
            @PathVariable Long id,
            @RequestBody TravelScheduleInputDto inputDto
    ){
        var updatedSchedule = service.updateTravelSchedule(
                id,
                inputDto
        );
        return ResponseEntity.ok(updatedSchedule);
    }

    @DeleteMapping("/{id-travel}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable("id-travel") Long id){
        service.deleteTravelSchedule(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/travel/{travel-id}")
    public ResponseEntity<List<TravelScheduleOutputDto>> getAllFlightSchedulesByFlightId(
            @PathVariable("{travel-id}") Long travelId
    ){
        List<TravelScheduleOutputDto> travelSchedules = service.getAllTravelSchedulesByTravelId(travelId);
        return ResponseEntity.ok(travelSchedules);
    }

}
