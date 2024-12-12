package com.microservices.businformationservice.controller;

import com.microservices.businformationservice.dto.BusInformationDto;
import com.microservices.businformationservice.service.BusInformationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequiredArgsConstructor
public class TravelInformationController {
    private final BusInformationService service;

    @PostMapping
    private ResponseEntity<BusInformationDto> addTravel(
        @RequestBody BusInformationDto busInformationDto
    ){
        var createdTravel = service.addTravel(busInformationDto);
        return ResponseEntity.ok(createdTravel);
    }

    @GetMapping("/{travel-id}")
    public ResponseEntity<BusInformationDto> getTravelbyId(
            @PathVariable("travel-id") Long travelId
    ){
        return ResponseEntity.ok(service.getTravelById(travelId));
    }
    @GetMapping
    public ResponseEntity<List<BusInformationDto>> getAllTravels(){
        List<BusInformationDto> travels = service.getAllTravels();
        return ResponseEntity.ok(travels);
    }

    @PutMapping("/{travel-id}")
    public ResponseEntity<BusInformationDto> updateTravel(
            @PathVariable("travel-id") Long travelId,
            @RequestBody BusInformationDto busInformationDto
    ){
        return ResponseEntity.ok(service.updateTravel(travelId,busInformationDto));
    }

    @DeleteMapping("/{travel-id}")
    public ResponseEntity<Void> deleteTravel(
            @PathVariable("travel-id") Long id
    ){
        service.deleteTravel(id);
        return ResponseEntity.noContent().build();
    }


}
