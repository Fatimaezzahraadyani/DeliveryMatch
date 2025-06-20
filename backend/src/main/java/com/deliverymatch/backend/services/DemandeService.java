package com.deliverymatch.backend.services;

import com.deliverymatch.backend.dto.ColisDTO;
import com.deliverymatch.backend.dto.DemandeDTO;
import com.deliverymatch.backend.dto.DemandeResponseDTO;
import com.deliverymatch.backend.model.*;
import com.deliverymatch.backend.repository.DemandeRepository;
import com.deliverymatch.backend.repository.SenderRepository;
import com.deliverymatch.backend.repository.TrajetRepository;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DemandeService {

    public final DemandeRepository demandeRepository;
    public final SenderRepository senderRepository;
    public final TrajetService trajetService;
    private final TrajetRepository trajetRepository;
    private final DriverService driverService;


    public DemandeService(SenderRepository senderRepository, TrajetService trajetService, DemandeRepository demandeRepository, TrajetRepository trajetRepository, DriverService driverService) {
        this.senderRepository = senderRepository;
        this.trajetService = trajetService;
        this.demandeRepository = demandeRepository;
        this.trajetRepository = trajetRepository;
        this.driverService = driverService;
    }

    public List<Demandes> getAllDemandesToAdmin() {
        return demandeRepository.findAll();
    }

    public ResponseEntity<?> CreateDemande(DemandeDTO demandeDTO) {
        Optional<Sender> SenderExist = senderRepository.findById(demandeDTO.senderId());
        Optional<Trajet> trajetExist = trajetRepository.findById(demandeDTO.trajetId());

        if(SenderExist.isEmpty() || trajetExist.isEmpty()) {
            return ResponseEntity.badRequest().body("Sender id or trajet id not found");
        }

        Demandes demande = new Demandes();
        demande.setDateDemande(new Date());
        demande.setSender(SenderExist.get());
        demande.setTrajet(trajetExist.get());
        demande.setStatutDemande(demandeDTO.statutDemande());
        demande.setSizeAvaileble(demandeDTO.sizeAvaileble());

        List<Colis> colisList = new ArrayList<>();
        if (demandeDTO.colis() != null) {
            for (ColisDTO c : demandeDTO.colis()) {
                Colis colis = new Colis();
                colis.setSize(c.Size());
                colis.setType(c.Type());
                colis.setWeight(c.weight());
                colisList.add(colis);
            }
        }
        // Enregistrer les colis et les associer à la demande
        demande.setColis(colisList);


        demandeRepository.save(demande);
        return ResponseEntity.ok().body("Demande created");
    }

    public ResponseEntity<?> UpdateDemande(DemandeDTO demandeDTO) {
        Optional<Sender> SenderExist = senderRepository.findById(demandeDTO.senderId());
        Optional<Trajet> TrajetExist = trajetRepository.findById(demandeDTO.trajetId());

        if(SenderExist.isEmpty() || TrajetExist.isEmpty()) {
            return ResponseEntity.badRequest().body("Sender id or trajet id not found");
        }
        Demandes demande = new Demandes();
        demande.setDateDemande(new Date());
        demande.setSender(SenderExist.get());
        demande.setTrajet(TrajetExist.get());
        demande.setStatutDemande(demandeDTO.statutDemande());
        demande.setSizeAvaileble(demandeDTO.sizeAvaileble());
        List<Colis> colisList = new ArrayList<>();
        if (demandeDTO.colis() != null) {
            for (ColisDTO c : demandeDTO.colis()) {
                Colis colis = new Colis();
                colis.setSize(c.Size());
                colis.setType(c.Type());
                colis.setWeight(c.weight());
                colisList.add(colis);
            }
        }
        demande.setColis(colisList);


        demandeRepository.save(demande);
        return ResponseEntity.ok().body("Demande updated");
    }

    public List<DemandeResponseDTO> getAllDemandesInfos() {
        return demandeRepository.findAll().stream()
                .map(demande -> new DemandeResponseDTO(
                        demande.getId(),
                        demande.getDateDemande(),
                        demande.getSizeAvaileble(),
                        demande.getStatutDemande(),
                        demande.getSender().getFirstName() + " " + demande.getSender().getLastName(),
                        demande.getTrajet().getStartPoint(),
                        demande.getTrajet().getEndPoint(),
                        demande.getTrajet().getTypeMarchandise(),
                        demande.getTrajet().getSizeMax()
                ))
                .collect(Collectors.toList());
    }


    public List<Demandes> getDemandesBySenderId(Long senderId) {
        return demandeRepository.findBySenderId(senderId);
    }

    public List<Colis> getColisByDemandeId(Long demandId) {
        Demandes demandes = demandeRepository.findById(demandId).orElseThrow(()->new RuntimeException("Demande not found"));

        return demandes.getColis();
    }

    public List<DemandeDTO> getAllDemandesAvecColis() {
        List<Demandes> demandes = demandeRepository.findAll();

        return demandes.stream().map(demande -> new DemandeDTO(
                demande.getSender().getId(),
                demande.getTrajet().getId(),
                demande.getSizeAvaileble(),
                demande.getStatutDemande(),
                demande.getColis().stream().map(colis -> new ColisDTO(
                        colis.getSize(),
                        colis.getType(),
                        colis.getWeight()
                )).toList()
        )).toList();
    }

    public List<DemandeDTO> getDemandesByDriverId(Long driverId) {
        List<Demandes> demandes = demandeRepository.findDemandesByDriverId(driverId);

        return demandes.stream().map(demande -> new DemandeDTO(
                demande.getSender().getId(),
                demande.getTrajet().getId(),
                demande.getSizeAvaileble(),
                demande.getStatutDemande(),
                demande.getColis().stream().map(colis -> new ColisDTO(
                        colis.getSize(),
                        colis.getType(),
                        colis.getWeight()
                )).toList()

        )).toList();

    }




}
