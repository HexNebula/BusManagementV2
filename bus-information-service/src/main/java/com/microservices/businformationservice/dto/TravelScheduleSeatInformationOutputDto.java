package com.microservices.businformationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TravelScheduleSeatInformationOutputDto {
    private Long id;
    private Boolean seatAvailability;
    private TravelScheduleOutputDto travelScheduleOutputDto;
}
