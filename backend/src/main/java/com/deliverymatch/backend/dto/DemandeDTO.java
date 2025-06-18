package com.deliverymatch.backend.dto;

import com.deliverymatch.backend.model.StatutDemende;
import jakarta.validation.constraints.NotNull;

public record DemandeDTO(
        @NotNull Long senderId,
        @NotNull Long trajetId,
        @NotNull String sizeAvaileble,
        @NotNull StatutDemende statutDemande
) {
}
