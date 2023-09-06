package com.betrybe.agrix.ebytr.staff.controller.dto;

import com.betrybe.agrix.ebytr.staff.model.entity.Person;
import com.betrybe.agrix.ebytr.staff.security.Role;

/**
 * Objeto para transferencia de dados da resposta Person.
 */
public record PersonResponseDto(Long id, String username, Role role) {

  /**
   * Retorna o objeto de transferência.
   */
  public static PersonResponseDto personToDto(Person person) {
    return new PersonResponseDto(
            person.getId(),
            person.getUsername(),
            person.getRole()
    );
  }
}
