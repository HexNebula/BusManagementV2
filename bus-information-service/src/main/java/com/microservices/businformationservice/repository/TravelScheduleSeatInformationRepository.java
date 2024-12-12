package com.microservices.businformationservice.repository;

import com.microservices.businformationservice.model.TravelScheduleSeatInformation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TravelScheduleSeatInformationRepository extends JpaRepository<TravelScheduleSeatInformation, Long> {
    List<TravelScheduleSeatInformation> findByTravelScheduleId(Long travelScheduleId);
    List<TravelScheduleSeatInformation> findByBookingStatus(Boolean bookingStatus);
}
