package com.betrybe.agrix.ebytr.staff.controller.dto;

import com.betrybe.agrix.ebytr.staff.model.entity.Person;
import com.betrybe.agrix.ebytr.staff.security.Role;

/**
 * Objeto para transferencia de dados da entrada Person.
 */
public record PersonDto(String username, String password, Role role) {

  /**
   * Retorna uma nova entidade Person.
   */
  public static Person toEntityPerson(PersonDto personDto) {
    Person newPerson = new Person();
    newPerson.setUsername(personDto.username());
    newPerson.setPassword(personDto.password());
    newPerson.setRole(personDto.role());

    return newPerson;
  }
}
