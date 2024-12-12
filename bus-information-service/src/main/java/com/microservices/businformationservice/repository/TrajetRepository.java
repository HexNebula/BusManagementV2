package com.microservices.businformationservice.repository;

import com.microservices.businformationservice.model.Trajet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Set;
@Repository
public interface TrajetRepository extends JpaRepository<Trajet,Long> {
    Set<Trajet> findAllByIdIn(Collection<Long> ids);
}
