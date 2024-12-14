package com.microservices.busapp.mapper;

import com.microservices.busapp.dto.CityDto;
import com.microservices.busapp.model.City;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CityMapper {
    public City mapToCity(CityDto cityDto){
        if(cityDto == null){
            return null;
        }
        return new City(
                cityDto.getId(),
                cityDto.getName(),
                cityDto.getCountry()
        );
    }

    public CityDto mapToCityDto(City city){
        if(city == null){
            return null;
        }
        return new CityDto(
                city.getId(),
                city.getName(),
                city.getCountry()
        );
    }
}
