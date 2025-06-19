package com.deliverymatch.backend.controller;

import com.deliverymatch.backend.dto.DemandeDTO;
import com.deliverymatch.backend.dto.DemandeResponseDTO;
import com.deliverymatch.backend.model.Colis;
import com.deliverymatch.backend.model.Demandes;
import com.deliverymatch.backend.services.DemandeService;
import com.deliverymatch.backend.services.UserServices;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sender/demandes")
public class DemandeController {

    private final DemandeService demandeService;
    public final UserServices userServices;

    public DemandeController(DemandeService demandeService, UserServices userServices) {
        this.demandeService = demandeService;
        this.userServices = userServices;
    }

    @GetMapping("/getAllToAdmin")
    public ResponseEntity<List<Demandes>> getAll() {
        return ResponseEntity.ok(demandeService.getAllDemandesToAdmin());
    }

    @GetMapping("/getallToSender")
    public ResponseEntity<List<DemandeResponseDTO>> getAllToSender() {
        return ResponseEntity.ok(demandeService.getAllDemandesInfos());
    }

    @GetMapping("/senderDemandes")
    public ResponseEntity<List<Demandes>> getDemandesBySender(Authentication authentication) {
        String email = authentication.getName();
        Long senderId = userServices.getSenderIdByEmail(email);
        List<Demandes> demandes = demandeService.getDemandesBySenderId(senderId);
        return ResponseEntity.ok(demandes);
    }

    @GetMapping("/all")
    public ResponseEntity<List<DemandeDTO>> getAllDemandesWithColis() {
        return ResponseEntity.ok(demandeService.getAllDemandesAvecColis());
    }


    @PostMapping
    public ResponseEntity<?> createDemande(@RequestBody @Valid DemandeDTO demandeDTO){
        return demandeService.CreateDemande(demandeDTO);
    }

    @GetMapping("/{demandeId}/Colis")
    public ResponseEntity<List<Colis>> getColisByDemandeId(@PathVariable Long demandeId){
        List<Colis> colisList = demandeService.getColisByDemandeId(demandeId);
        return ResponseEntity.ok(colisList);
    }





}
