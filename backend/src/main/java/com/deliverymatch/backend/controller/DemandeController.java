package com.deliverymatch.backend.controller;

import com.deliverymatch.backend.dto.DemandeDTO;
import com.deliverymatch.backend.dto.DemandeResponseDTO;
import com.deliverymatch.backend.model.Colis;
import com.deliverymatch.backend.model.Demandes;
import com.deliverymatch.backend.model.StatutDemande;
import com.deliverymatch.backend.model.Trajet;
import com.deliverymatch.backend.repository.DemandeRepository;
import com.deliverymatch.backend.services.DemandeService;
import com.deliverymatch.backend.services.UserServices;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/sender/demandes")
public class DemandeController {

    private final DemandeService demandeService;
    public final UserServices userServices;
    private final DemandeRepository demandeRepository;

    public DemandeController(DemandeService demandeService, UserServices userServices, DemandeRepository demandeRepository) {
        this.demandeService = demandeService;
        this.userServices = userServices;
        this.demandeRepository = demandeRepository;
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
    @PutMapping("/updateDemande")
    public ResponseEntity<?> updateDemande(@RequestBody @Valid DemandeDTO demandeDTO){
        return demandeService.UpdateDemande(demandeDTO);
    }

    @PutMapping("/demandes/{id}/statut")
    public ResponseEntity<String> updateDemandeStatut(@PathVariable Long id, @RequestParam ("statut") StatutDemande newStatutDemande ){
        Optional<Demandes> optionalDemande = demandeRepository.findById(id);

        if (optionalDemande.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        Demandes demande = optionalDemande.get();
        demande.setStatutDemande(newStatutDemande);

        if (newStatutDemande == StatutDemande.ACCEPTED){
            Trajet trajet = demande.getTrajet();
            if(trajet.getAvailebleCapacity() <= 0){
                return ResponseEntity.badRequest().body("capacité insuffisante");
            }
            trajet.setAvailebleCapacity(trajet.getAvailebleCapacity() - 1);
        }
        demandeRepository.save(demande);
        return ResponseEntity.ok("Statut mis à jour");
    }


}
