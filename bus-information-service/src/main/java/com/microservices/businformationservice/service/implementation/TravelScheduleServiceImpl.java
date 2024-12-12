package com.microservices.businformationservice.service.implementation;

import com.microservices.businformationservice.dto.TravelScheduleInputDto;
import com.microservices.businformationservice.dto.TravelScheduleOutputDto;
import com.microservices.businformationservice.mapper.TravelScheduleMapper;
import com.microservices.businformationservice.model.TravelSchedule;
import com.microservices.businformationservice.repository.BusInformationRepository;
import com.microservices.businformationservice.repository.TravelScheduleRepository;
import com.microservices.businformationservice.service.TravelScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TravelScheduleServiceImpl implements TravelScheduleService {

    public final TravelScheduleRepository travelScheduleRepository;
    public final BusInformationRepository busInformationRepository;
    public final TravelScheduleMapper mapper;

    @Override
    public TravelScheduleOutputDto addTravelSchedule(TravelScheduleInputDto inputDto) {
        TravelSchedule travelSchedule = mapper.mapToTravelSchedule(inputDto);
        TravelSchedule savedTravelSchedule = travelScheduleRepository.save(travelSchedule);
        return mapper.mapToTravelScheduleOutputDto(savedTravelSchedule);
    }

    @Override
    public TravelScheduleOutputDto getTravelScheduleById(Long id) {
        TravelSchedule travelSchedule = travelScheduleRepository
                .findById(id)
                .orElseThrow(()->new RuntimeException("Travel schedule does not exist"));
        return mapper.mapToTravelScheduleOutputDto(travelSchedule);
    }

    @Override
    public List<TravelScheduleOutputDto> getAllTravelSchedules() {
        List<TravelSchedule> scheduleList = travelScheduleRepository.findAll();
        return scheduleList.stream()
                .map(mapper::mapToTravelScheduleOutputDto)
                .collect(Collectors.toList());
    }

    @Override
    public TravelScheduleOutputDto updateTravelSchedule(Long id, TravelScheduleInputDto inputDto) {
        LocalDateTime departureDateTime = LocalDateTime.of(inputDto.getDepartureDate(), inputDto.getDepartureTime());
        LocalDateTime arrivalDateTime = LocalDateTime.of(inputDto.getArrivalDate(), inputDto.getArrivalTime());
        TravelSchedule travelSchedule = travelScheduleRepository
                .findById(id)
                .orElseThrow(()->new RuntimeException("Travel schedule does not exist"));
        travelSchedule.setBus(
                busInformationRepository.findById(inputDto.getBusInformationId())
                        .orElseThrow(()->new RuntimeException("Bus id not found"))
        );
        travelSchedule.setDepartureCity(inputDto.getDepartureCity());
        travelSchedule.setArrivalCity(inputDto.getArrivalCity());
        travelSchedule.setDepartureTime(departureDateTime);
        travelSchedule.setArrivalTime(arrivalDateTime);
        TravelSchedule updatedTravelSchedule = travelScheduleRepository.save(travelSchedule);
        return mapper.mapToTravelScheduleOutputDto(updatedTravelSchedule);
    }

    @Override
    public void deleteTravelSchedule(Long id) {
        TravelSchedule travelSchedule = travelScheduleRepository
                .findById(id)
                .orElseThrow(()->new RuntimeException("No travel found with this ID"));
        travelScheduleRepository.deleteById(id);
    }

    @Override
    public List<TravelScheduleOutputDto> getAllTravelSchedulesByTravelId(Long travelId) {
        List<TravelSchedule> travelSchedules = travelScheduleRepository.findByBus_Id(travelId);
        return travelSchedules
                .stream()
                .map(mapper::mapToTravelScheduleOutputDto)
                .collect(Collectors.toList());
    }
}
