package com.deliverymatch.backend.dto;

import com.deliverymatch.backend.model.StatutDemande;

import java.util.Date;

public record DemandeResponseDTO(

        Long id,
        Date dateDemande,
        String sizeAvaileble,
        StatutDemande statutDemande,
        String senderName,
        String startPoint,
        String endPoint,
        String typeMarchandise,
        String sizeMax
) {
}
