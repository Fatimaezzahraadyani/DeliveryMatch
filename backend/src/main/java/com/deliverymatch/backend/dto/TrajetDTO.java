package com.deliverymatch.backend.dto;

import java.util.Date;
import java.util.List;

public record TrajetDTO(
        String StartPoint,
        String EndPoint,
        List<String> passByPoints,
        Date StartDate,
        String TypeMarchandise,
        String SizeMax,
        int AvailebleCapacity

) {
}
