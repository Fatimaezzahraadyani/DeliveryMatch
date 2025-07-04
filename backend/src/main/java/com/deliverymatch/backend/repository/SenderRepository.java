package com.deliverymatch.backend.repository;

import com.deliverymatch.backend.model.Sender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SenderRepository extends JpaRepository<Sender, Long> {

    Optional<Sender> findByEmail(String email);

}
