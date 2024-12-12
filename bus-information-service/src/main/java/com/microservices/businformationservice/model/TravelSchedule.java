package com.microservices.businformationservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class TravelSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private BusInformation bus;

    @ManyToOne(fetch = FetchType.LAZY)
    private City departureCity;  // City where the bus departs from

    @ManyToOne(fetch = FetchType.LAZY)
    private City arrivalCity;    // City where the bus arrives

    private LocalDateTime departureTime;

    private LocalDateTime arrivalTime;


    public String getScheduleName() {
        // Dynamic generation of schedule name based on bus, cities, and times
        return String.format("%s - %s to %s, %s",
                bus.getBusNumber(),
                departureCity.getName(),
                arrivalCity.getName(),
                departureTime.format(DateTimeFormatter.ofPattern("hh:mm a"))
        );
    }

    public long getDurationMinutes() {
        return java.time.Duration.between(departureTime, arrivalTime).toMinutes();
    }
}