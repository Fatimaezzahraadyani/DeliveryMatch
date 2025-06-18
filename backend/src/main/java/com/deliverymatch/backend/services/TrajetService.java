package com.deliverymatch.backend.services;


import com.deliverymatch.backend.dto.TrajetDTO;
import com.deliverymatch.backend.model.Driver;
import com.deliverymatch.backend.model.Trajet;
import com.deliverymatch.backend.repository.DriverRepository;
import com.deliverymatch.backend.repository.TrajetRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class TrajetService {

    private final DriverRepository driverRepository;
    private final TrajetRepository trajetRepository;


    public TrajetService(DriverRepository driverRepository, TrajetRepository trajetRepository) {
        this.driverRepository = driverRepository;
        this.trajetRepository = trajetRepository;
    }

    public List<Trajet> getAllTrajets() {
        return trajetRepository.findAll();
    }


    public Trajet publiertTrajet(Long driverId, TrajetDTO trajetDTO) {
        Driver driver = driverRepository.findById(driverId).orElseThrow(()-> new RuntimeException("Driver not found"));
        Trajet trajet = new Trajet();
        trajet.setStartPoint(trajetDTO.StartPoint());
        trajet.setEndPoint(trajetDTO.EndPoint());
        trajet.setPassByPoints(trajetDTO.passByPoints());
        trajet.setAvailebleCapacity(trajetDTO.AvailebleCapacity());
        trajet.setStartDate(trajetDTO.StartDate());
        trajet.setDatePublication(new Date());
        trajet.setSizeMax(trajetDTO.SizeMax());
        trajet.setAvailebleCapacity(trajetDTO.AvailebleCapacity());
        trajet.setDriver(driver);

        return trajetRepository.save(trajet);

    }

    public Long getDriverIdByEmail(String email) {
        Driver driver = driverRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Conducteur non trouvé avec l'email : " + email));
        return driver.getId();
    }

    // Modifier trajet
    public Trajet updateTrajet(Long id , TrajetDTO trajetDTO) {
        Trajet trajet = trajetRepository.findById(id).orElseThrow();
        trajet.setStartPoint(trajetDTO.StartPoint());
        trajet.setEndPoint(trajetDTO.EndPoint());
        trajet.setPassByPoints(trajetDTO.passByPoints());
        trajet.setAvailebleCapacity(trajetDTO.AvailebleCapacity());
        trajet.setStartDate(trajetDTO.StartDate());
        trajet.setDatePublication(new Date());
        trajet.setSizeMax(trajetDTO.SizeMax());

        return trajetRepository.save(trajet);
    }

    public void deleteTrajet(Long id) {
        Trajet trajet = trajetRepository.findById(id).orElseThrow();
        trajetRepository.delete(trajet);
    }


}
