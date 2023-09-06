package com.betrybe.agrix.ebytr.staff.controller;



import com.betrybe.agrix.ebytr.staff.controller.dto.PersonDto;
import com.betrybe.agrix.ebytr.staff.controller.dto.PersonResponseDto;
import com.betrybe.agrix.ebytr.staff.model.entity.Person;
import com.betrybe.agrix.ebytr.staff.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Classe Controller apondada para Person.
 */

@RestController
@RequestMapping("/persons")
public class PersonController {

  private final PersonService personService;

  /**
   * Constructor.
   */
  @Autowired
  public PersonController(PersonService personService) {
    this.personService = personService;
  }

  /**
   * Cria entidade Person.
   */
  @PostMapping
  public ResponseEntity<PersonResponseDto> create(@RequestBody PersonDto personData) {
    Person newEntityPerson = personService.create(PersonDto.toEntityPerson(personData));

    PersonResponseDto newPerson = PersonResponseDto.personToDto(newEntityPerson);

    return ResponseEntity.status(HttpStatus.CREATED).body(newPerson);

  }
}
