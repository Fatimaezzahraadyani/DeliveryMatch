package com.deliverymatch.backend.services;

import com.deliverymatch.backend.dto.DemandeDTO;
import com.deliverymatch.backend.model.Demandes;
import com.deliverymatch.backend.model.Sender;
import com.deliverymatch.backend.model.Trajet;
import com.deliverymatch.backend.repository.DemandeRepository;
import com.deliverymatch.backend.repository.SenderRepository;
import com.deliverymatch.backend.repository.TrajetRepository;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class DemandeService {

    public final DemandeRepository demandeRepository;
    public final SenderRepository senderRepository;
    public final TrajetService trajetService;
    private final TrajetRepository trajetRepository;


    public DemandeService(SenderRepository senderRepository, TrajetService trajetService, DemandeRepository demandeRepository, TrajetRepository trajetRepository) {
        this.senderRepository = senderRepository;
        this.trajetService = trajetService;
        this.demandeRepository = demandeRepository;
        this.trajetRepository = trajetRepository;
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

        demandeRepository.save(demande);
        return ResponseEntity.ok().body("Demande created");
    }


}
