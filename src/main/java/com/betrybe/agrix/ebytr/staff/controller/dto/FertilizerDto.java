package com.betrybe.agrix.ebytr.staff.controller.dto;

import com.betrybe.agrix.ebytr.staff.model.entity.Fertilizer;


/**
 * Objeto para transferencia de dados Fertilizer.
 */
public record FertilizerDto(Integer id, String name, String brand, String composition) {

  public Fertilizer toFertilizer() {

    return new Fertilizer(id, name, brand, composition);
  }
}

