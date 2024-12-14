package com.microservices.busapp.mapper;

import com.microservices.busapp.dto.ArretDto;
import com.microservices.busapp.dto.TrajetDto;
import com.microservices.busapp.model.Arret;
import com.microservices.busapp.model.Trajet;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TrajetMapper {
    private final CityMapper cityMapper;
    private final ArretMapper arretMapper;
    public TrajetDto toDto(Trajet trajet){
        if(trajet == null) return null;
        List<ArretDto> arrets = trajet.getStops().stream()
                .map(arretMapper::toDto)
                .collect(Collectors.toList());
        return TrajetDto.builder()
                .id(trajet.getId())
                .depart(cityMapper.mapToCityDto(trajet.getDepart()))
                .arrivee(cityMapper.mapToCityDto(trajet.getArrivee()))
                .stops(arrets)
                .build();
    }
    public Trajet toEntity(TrajetDto trajetDto){
        if(trajetDto == null) return null;
        List<Arret> arrets = trajetDto.getStops()
                .stream()
                .map(arretMapper::toEntity)
                .collect(Collectors.toList());
        return Trajet.builder()
                .depart(cityMapper.mapToCity(trajetDto.getDepart()))
                .arrivee(cityMapper.mapToCity(trajetDto.getArrivee()))
                .stops(arrets)
                .buses(new HashSet<>())
                .build();
    }
}