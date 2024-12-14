package com.microservices.busapp.mapper;

import com.microservices.busapp.dto.ArretDto;
import com.microservices.busapp.model.Arret;
import com.microservices.busapp.repository.ArretRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArretMapper {
    public ArretDto toDto(Arret arret){
        if(arret == null){
            return null;
        }
        return ArretDto.builder()
                .id(arret.getId())
                .name(arret.getName())
                .latitude(arret.getLatitude())
                .longitude(arret.getLongitude())
                .build();
    }
    public Arret toEntity(ArretDto arretDto){
        if(arretDto == null){
            return null;
        }
        return Arret.builder()
                .id(arretDto.getId())
                .name(arretDto.getName())
                .latitude(arretDto.getLatitude())
                .longitude(arretDto.getLongitude())
                .build();
    }
    public List<ArretDto> toDtoList(List<Arret> arrets){
        return arrets.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
    public List<Arret> toEntityList(List<ArretDto> arretDtos){
        return arretDtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
