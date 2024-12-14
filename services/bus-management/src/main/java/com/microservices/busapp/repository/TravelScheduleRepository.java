package com.microservices.busapp.repository;

import com.microservices.busapp.model.TravelSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TravelScheduleRepository extends JpaRepository<TravelSchedule, Long> {
    List<TravelSchedule> findByBus_Id(Long travelId);
}
