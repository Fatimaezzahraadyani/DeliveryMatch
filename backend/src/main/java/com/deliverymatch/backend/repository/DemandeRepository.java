package com.deliverymatch.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.deliverymatch.backend.model.Demandes;

import java.util.List;

public interface DemandeRepository extends JpaRepository<Demandes, Long> {


    List<Demandes> findBySenderId(Long senderId);
}
