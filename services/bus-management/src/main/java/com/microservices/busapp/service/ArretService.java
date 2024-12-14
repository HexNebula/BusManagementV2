package com.microservices.busapp.service;

import com.microservices.busapp.dto.ArretDto;
import com.microservices.busapp.mapper.ArretMapper;
import com.microservices.busapp.model.Arret;
import com.microservices.busapp.repository.ArretRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArretService {
    private final ArretRepository repository;
    private final ArretMapper mapper;
    public List<ArretDto> getAllStops(){
        List<Arret> arrets = repository.findAll();
        return mapper.toDtoList(arrets);
    }
    public ArretDto createStop(ArretDto arretDto){
        Arret arret = mapper.toEntity(arretDto);
        return mapper.toDto(repository.save(arret));
    }
}
