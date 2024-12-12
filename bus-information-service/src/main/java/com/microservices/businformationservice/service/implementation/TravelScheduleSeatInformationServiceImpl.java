package com.microservices.businformationservice.service.implementation;

import com.microservices.businformationservice.dto.TravelScheduleSeatInformationInputDto;
import com.microservices.businformationservice.dto.TravelScheduleSeatInformationOutputDto;
import com.microservices.businformationservice.mapper.TravelScheduleSeatInformationMapper;
import com.microservices.businformationservice.model.TravelSchedule;
import com.microservices.businformationservice.model.TravelScheduleSeatInformation;
import com.microservices.businformationservice.repository.TravelScheduleRepository;
import com.microservices.businformationservice.repository.TravelScheduleSeatInformationRepository;
import com.microservices.businformationservice.service.TravelScheduleSeatInformationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TravelScheduleSeatInformationServiceImpl implements TravelScheduleSeatInformationService {

    private final TravelScheduleSeatInformationRepository seatInformationRepository;
    private final TravelScheduleRepository travelScheduleRepository;
    private final TravelScheduleSeatInformationMapper mapper;


    @Override
    public TravelScheduleSeatInformationOutputDto addSeatInformation(TravelScheduleSeatInformationInputDto inputDto) {
        TravelSchedule travelSchedule = travelScheduleRepository.findById(inputDto.getTravelScheduleId())
                .orElseThrow(()->new RuntimeException("Travel Schedule Not Found"));
        TravelScheduleSeatInformation seatInformation = mapper.mapToTravelSeatInformation(inputDto,travelSchedule);
        TravelScheduleSeatInformation saveInformation = seatInformationRepository.save(seatInformation);
        return mapper.mapToSeatOutputDto(saveInformation);
    }

    @Override
    public TravelScheduleSeatInformationOutputDto getSeatInformationById(Long id) {
        TravelScheduleSeatInformation seatInformation = seatInformationRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Seat information not found"));
        return mapper.mapToSeatOutputDto(seatInformation);
    }

    @Override
    public List<TravelScheduleSeatInformationOutputDto> getAllSeatInformation() {
        List<TravelScheduleSeatInformation> seatInformationList = seatInformationRepository.findAll();
        return seatInformationList
                .stream()
                .map(mapper::mapToSeatOutputDto)
                .collect(Collectors.toList());
    }

    @Override
    public TravelScheduleSeatInformationOutputDto updateSeatInformation(Long id, TravelScheduleSeatInformationInputDto inputDto) {
        TravelScheduleSeatInformation seatInformation = seatInformationRepository
                .findById(id)
                .orElseThrow(()->new RuntimeException("Seat information does not exist"));
        TravelSchedule travelSchedule = travelScheduleRepository
                .findById(inputDto.getId())
                .orElseThrow(()->new RuntimeException("Travel schedule not found"));
        seatInformation.setBookingStatus(inputDto.getSeatAvailability());
        seatInformation.setTravelSchedule(travelSchedule);
        TravelScheduleSeatInformation updatedSeatInformation = seatInformationRepository.save(seatInformation);
        return mapper.mapToSeatOutputDto(updatedSeatInformation);
    }

    @Override
    public void deleteSeatInformation(Long id) {
        TravelScheduleSeatInformation seatInformation = seatInformationRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Seat information does not exist"));
        seatInformationRepository.delete(seatInformation);
    }

    @Override
    public List<TravelScheduleSeatInformationOutputDto> getSeatInformationByTravelScheduleId(Long travelScheduleId) {
        return seatInformationRepository.findByTravelScheduleId(travelScheduleId).stream()
                .map(mapper::mapToSeatOutputDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TravelScheduleSeatInformationOutputDto> getSeatInformationByBookingStatus(Boolean seatAvailability) {
        return seatInformationRepository.findByBookingStatus(seatAvailability)
                .stream()
                .map(mapper::mapToSeatOutputDto)
                .collect(Collectors.toList());
    }
}
