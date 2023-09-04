package com.betrybe.agrix.ebytr.staff.controller.dto;

import java.time.LocalDate;

/**
 * Objeto para transferencia de dados Crop.
 */
public record CropDto(
        Integer id,
        String name,
        Double plantedArea,
        LocalDate plantedDate,
        LocalDate harvestDate,
        Integer farmId
) {

}

