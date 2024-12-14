package com.microservices.busapp.controller;

import com.microservices.busapp.dto.TravelScheduleSeatInformationInputDto;
import com.microservices.busapp.dto.TravelScheduleSeatInformationOutputDto;
import com.microservices.busapp.service.TravelScheduleSeatInformationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bus_reservation/api/seats")
@RequiredArgsConstructor
public class TravelScheduleSeatInformationController {
    private final TravelScheduleSeatInformationService service;


    @PostMapping
    public ResponseEntity<TravelScheduleSeatInformationOutputDto> addSeatInformation(
            @RequestBody TravelScheduleSeatInformationInputDto inputDto
    ){
        TravelScheduleSeatInformationOutputDto savedSeatInfo = service.addSeatInformation(inputDto);
        return ResponseEntity.ok(savedSeatInfo);
    }

    @GetMapping("/{seat-id}")
    public ResponseEntity<TravelScheduleSeatInformationOutputDto> getSeatInformationById(
            @PathVariable("seat-id") Long id
    ){
        TravelScheduleSeatInformationOutputDto seatInfo = service.getSeatInformationById(id);
        return ResponseEntity.ok(seatInfo);
    }

    @PutMapping("/{seat-id}")
    public ResponseEntity<TravelScheduleSeatInformationOutputDto> updateSeatInformationById(
            @PathVariable("seat-id") Long id,
            @RequestBody TravelScheduleSeatInformationInputDto inputDto
    ){
        TravelScheduleSeatInformationOutputDto updatedSeatInfo = service.updateSeatInformation(id, inputDto);
        return ResponseEntity.ok(updatedSeatInfo);
    }

    @DeleteMapping("/{seat-id}")
    public ResponseEntity<Void> deleteSeatInformation(
            @PathVariable("seat-id") Long id
    ){
        service.deleteSeatInformation(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/travel-schedule/{travelScheduleId}")
    public ResponseEntity<List<TravelScheduleSeatInformationOutputDto>> getSeatInformationByTravelScheduleId(
            @PathVariable("travelScheduleId") Long id
    ){
        List<TravelScheduleSeatInformationOutputDto> seatInfoList = service.getSeatInformationByTravelScheduleId(id);
        return ResponseEntity.ok(seatInfoList);
    }

    //    localhost:8083/api/seats/booking-status?bookingStatus=false
    @GetMapping("/booking-status")
    public ResponseEntity<List<TravelScheduleSeatInformationOutputDto>> getSeatInformationByBookingStatus(
            @RequestParam Boolean bookingStatus
    ){
        List<TravelScheduleSeatInformationOutputDto> seatInformationList = service.getSeatInformationByBookingStatus(bookingStatus);
        return ResponseEntity.ok(seatInformationList);
    }


}
