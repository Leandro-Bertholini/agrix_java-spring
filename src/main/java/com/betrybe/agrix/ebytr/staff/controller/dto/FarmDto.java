package com.betrybe.agrix.ebytr.staff.controller.dto;

import com.betrybe.agrix.ebytr.staff.model.entity.Farm;

/**
 * Objeto para transferencia de dados Farm.
 */
public record FarmDto(Integer id, String name, Double size) {

  public Farm toFarm() {

    return new Farm(id, name, size);
  }
}
