package com.microservices.businformationservice.mapper;

import com.microservices.businformationservice.dto.TravelScheduleOutputDto;
import com.microservices.businformationservice.dto.TravelScheduleSeatInformationInputDto;
import com.microservices.businformationservice.dto.TravelScheduleSeatInformationOutputDto;
import com.microservices.businformationservice.model.TravelSchedule;
import com.microservices.businformationservice.model.TravelScheduleSeatInformation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TravelScheduleSeatInformationMapper {
    private final TravelScheduleMapper mapper;
    public TravelScheduleSeatInformation mapToTravelSeatInformation(TravelScheduleSeatInformationInputDto inputDto, TravelSchedule travelSchedule){
        TravelScheduleSeatInformation seatInformation = new TravelScheduleSeatInformation();
        seatInformation.setId(inputDto.getId());
        seatInformation.setBookingStatus(inputDto.getSeatAvailability());
        seatInformation.setTravelSchedule(travelSchedule);

        return seatInformation;
    }

    public TravelScheduleSeatInformationOutputDto mapToSeatOutputDto(TravelScheduleSeatInformation seatInformation){
        TravelScheduleOutputDto outputDto = mapper.mapToTravelScheduleOutputDto(seatInformation.getTravelSchedule());
        return new TravelScheduleSeatInformationOutputDto(
                seatInformation.getId(),
                seatInformation.getBookingStatus(),
                outputDto
        );
    }
}
