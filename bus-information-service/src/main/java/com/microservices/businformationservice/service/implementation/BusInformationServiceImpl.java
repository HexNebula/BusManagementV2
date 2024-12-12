package com.microservices.businformationservice.service.implementation;

import com.microservices.businformationservice.dto.BusInformationDto;
import com.microservices.businformationservice.dto.TrajetDto;
import com.microservices.businformationservice.mapper.TrajetMapper;
import com.microservices.businformationservice.mapper.TravelInformationMapper;
import com.microservices.businformationservice.model.BusInformation;
import com.microservices.businformationservice.model.City;
import com.microservices.businformationservice.model.Trajet;
import com.microservices.businformationservice.repository.BusInformationRepository;
import com.microservices.businformationservice.repository.CityRepository;
import com.microservices.businformationservice.repository.TrajetRepository;
import com.microservices.businformationservice.service.BusInformationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class BusInformationServiceImpl implements BusInformationService {
    private final BusInformationRepository repository;
    private final TravelInformationMapper mapperTravel;
    private final TrajetRepository trajetRepository;
    private final CityRepository cityRepository;
    private final TrajetMapper trajetMapper;

    @Override
    public BusInformationDto addTravel(BusInformationDto informationDto){
        BusInformation travel = mapperTravel.mapToTravelInformation(informationDto);
        BusInformation savedTravel = repository.save(travel);
        return mapperTravel.mapToTravelInformationDto(savedTravel);
    }

    @Override
    public BusInformationDto getTravelById(Long id) {
        BusInformation travel = repository
                .findById(id)
                .orElseThrow(()->new RuntimeException("Pas de voyage avec cet ID"));
        return mapperTravel.mapToTravelInformationDto(travel);
    }

    @Override
    public List<BusInformationDto> getAllTravels() {
        List<BusInformation> travels = repository.findAll();
        return travels.stream()
                .map(mapperTravel::mapToTravelInformationDto)
                .collect(Collectors.toList());
    }

    @Override
    public BusInformationDto updateTravel(Long id, BusInformationDto informationDto) {
        // Find the existing BusInformation entity by ID
        BusInformation travel = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("No travel found with this ID"));

        // Update bus details
        travel.setBusNumber(informationDto.getBusNumber());
        travel.setCapacity(informationDto.getCapacity());
        travel.setBusType(informationDto.getBusType());
        travel.setServiceProvider(informationDto.getServiceProvider());

        // Set cities based on CityDto
        City departureCity = cityRepository.findById(informationDto.getDepartureCity().getId())
                .orElseThrow(() -> new RuntimeException("Departure city not found"));
        City arrivalCity = cityRepository.findById(informationDto.getArrivalCity().getId())
                .orElseThrow(() -> new RuntimeException("Arrival city not found"));

        travel.setDepartureCity(departureCity);
        travel.setArrivalCity(arrivalCity);

        // Set trajets based on TrajetDto
        Set<TrajetDto> trajets = informationDto.getTrajets();

        travel.setTrajets(trajets.stream().map(trajetMapper::mapToTrajet).collect(Collectors.toSet()));

        // Save the updated BusInformation entity
        BusInformation savedTravel = repository.save(travel);

        // Return the updated BusInformationDto
        return mapperTravel.mapToTravelInformationDto(savedTravel);
    }

    @Override
    public void deleteTravel(Long id) {
        repository.deleteById(id);
    }
}
