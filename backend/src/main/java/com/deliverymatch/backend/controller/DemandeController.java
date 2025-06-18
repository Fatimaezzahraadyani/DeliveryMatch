package com.deliverymatch.backend.controller;

import com.deliverymatch.backend.dto.DemandeDTO;
import com.deliverymatch.backend.services.DemandeService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sender/demandes")
public class DemandeController {

    private final DemandeService demandeService;

    public DemandeController(DemandeService demandeService) {
        this.demandeService = demandeService;
    }

    @PostMapping
    public ResponseEntity<?> createDemande(@RequestBody @Valid DemandeDTO demandeDTO){
        return demandeService.CreateDemande(demandeDTO);
    }
}
