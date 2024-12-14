package com.microservices.busapp.repository;

import com.microservices.busapp.model.TravelScheduleSeatInformation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TravelScheduleSeatInformationRepository extends JpaRepository<TravelScheduleSeatInformation, Long> {
    List<TravelScheduleSeatInformation> findByTravelScheduleId(Long travelScheduleId);
    List<TravelScheduleSeatInformation> findByBookingStatus(Boolean bookingStatus);
}
