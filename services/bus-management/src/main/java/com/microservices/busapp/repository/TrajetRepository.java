package com.microservices.busapp.repository;

import com.microservices.busapp.model.Trajet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Set;
@Repository
public interface TrajetRepository extends JpaRepository<Trajet,Long> {
    Set<Trajet> findAllByIdIn(Collection<Long> ids);
}
