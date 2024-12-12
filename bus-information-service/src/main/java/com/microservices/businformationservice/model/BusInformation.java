package com.microservices.businformationservice.model;

import com.microservices.businformationservice.dto.TrajetDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BusInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String busNumber; // Unique identifier for the bus
    private int capacity; // Number of passengers it can carry
    private String busType; // Autobus articule ou bus normal (aka double cabine)
    private String serviceProvider; // Local company managing the bus
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "departure_city_id")
    private City departureCity;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "arrival_city_id")
    private City arrivalCity;
    @ManyToMany
    @JoinTable(
            name = "bus_trajet",
            joinColumns = @JoinColumn(name = "bus_id"),
            inverseJoinColumns = @JoinColumn(name = "trajet_id")
    )
    private Set<Trajet> trajets; // A bus can be part of multiple trajectories (many-to-many relationship)
}
