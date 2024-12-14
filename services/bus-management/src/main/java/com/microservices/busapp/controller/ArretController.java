package com.microservices.busapp.controller;

import com.microservices.busapp.dto.ArretDto;
import com.microservices.busapp.service.ArretService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bus_reservation/api/stops")
@RequiredArgsConstructor
public class ArretController {
    private final ArretService service;
    @GetMapping
    public ResponseEntity<List<ArretDto>> getAllStops(){
        return ResponseEntity.ok(service.getAllStops());
    }
    @PostMapping
    public ResponseEntity<ArretDto> createStop(
            @RequestBody ArretDto request
    ){
        return ResponseEntity.ok(service.createStop(request));
    }
}
