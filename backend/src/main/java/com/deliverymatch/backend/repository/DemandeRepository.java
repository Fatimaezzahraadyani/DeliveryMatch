package com.deliverymatch.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.deliverymatch.backend.model.Demandes;

public interface DemandeRepository extends JpaRepository<Demandes, Long> {

}
