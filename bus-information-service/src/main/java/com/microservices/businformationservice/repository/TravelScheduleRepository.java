package com.microservices.businformationservice.repository;

import com.microservices.businformationservice.model.TravelSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TravelScheduleRepository extends JpaRepository<TravelSchedule, Long> {
    List<TravelSchedule> findByBus_Id(Long travelId);
}
