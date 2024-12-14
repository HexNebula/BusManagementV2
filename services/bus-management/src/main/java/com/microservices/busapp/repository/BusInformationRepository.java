package com.microservices.busapp.repository;

import com.microservices.busapp.model.BusInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusInformationRepository extends JpaRepository<BusInformation, Long> {
}
