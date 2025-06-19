package com.deliverymatch.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.deliverymatch.backend.model.Demandes;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DemandeRepository extends JpaRepository<Demandes, Long> {


    List<Demandes> findBySenderId(Long senderId);

    @Query("SELECT d FROM Demandes d WHERE d.trajet.driver.id = :driverId")
    List<Demandes> findDemandesByDriverId(@Param("driverId") Long driverId);
}
