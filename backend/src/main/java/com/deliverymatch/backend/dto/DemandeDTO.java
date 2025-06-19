package com.deliverymatch.backend.dto;

import com.deliverymatch.backend.model.StatutDemande;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record DemandeDTO(
        @NotNull Long senderId,
        @NotNull Long trajetId,
        @NotNull String sizeAvaileble,
        @NotNull StatutDemande statutDemande,

        List<ColisDTO> colis
) {
}
