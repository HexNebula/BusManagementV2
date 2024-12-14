package com.microservices.busapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Trajet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Unique identifier for the trajectory

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "departure_city_id", nullable = false)
    private City depart; // Departure city

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "arrival_city_id", nullable = false)
    private City arrivee; // Arrival city

    @ManyToMany
    @JoinTable(
            name = "trajet_arret",
            joinColumns = @JoinColumn(name = "trajet_id"),
            inverseJoinColumns = @JoinColumn(name = "arret_id")
    )
    private List<Arret> stops; // List of stops associated with the route


    @ManyToMany(mappedBy = "trajets")
    private Set<BusInformation> buses = new HashSet<>(); // A trajectory can have multiple buses passing through it


}