package com.microservices.busapp.repository;

import com.microservices.busapp.model.Arret;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArretRepository extends JpaRepository<Arret,Integer> {
}
