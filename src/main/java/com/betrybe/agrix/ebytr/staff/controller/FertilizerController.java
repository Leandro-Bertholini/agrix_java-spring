package com.betrybe.agrix.ebytr.staff.controller;

import com.betrybe.agrix.ebytr.staff.controller.dto.FertilizerDto;
import com.betrybe.agrix.ebytr.staff.model.entity.Fertilizer;
import com.betrybe.agrix.ebytr.staff.service.FertilizerService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Classe Controller apondada para fertilizante(Fertilizer).
 */

@RestController
@RequestMapping("/fertilizers")
public class FertilizerController {

  private final FertilizerService fertilizerService;

  /**
   * Constructor.
   */
  public FertilizerController(FertilizerService fertilizerService) {
    this.fertilizerService = fertilizerService;
  }


  /**
   * Inserir nova entidade fertilizante(Fertilizer).
   */
  @PostMapping
  public ResponseEntity<Fertilizer> createFertilizer(@RequestBody FertilizerDto fertilizerDto) {
    Fertilizer createdFertilizer = fertilizerService.createFertilizer(fertilizerDto.toFertilizer());

    return ResponseEntity.status(HttpStatus.CREATED).body(createdFertilizer);
  }

  /**
   * Capturar todas os fertililzantes cadastrados.
   */
  @GetMapping
  public List<FertilizerDto> getAllFertilizer() {
    List<Fertilizer> allFertilizers = fertilizerService.getAllFertilizer();

    return allFertilizers.stream()
            .map((fer) -> new FertilizerDto(
                    fer.getId(),
                    fer.getName(),
                    fer.getBrand(),
                    fer.getComposition()
            ))
            .collect(Collectors.toList());
  }

  /**
   * Captura o fertilizante pelo ID.
   */
  @GetMapping("/{id}")
  public ResponseEntity<FertilizerDto> getFertilizerById(@PathVariable Integer id) {
    Fertilizer fertilizer = fertilizerService.getFertilizerById(id);
    FertilizerDto fertilizerByIdDto = new FertilizerDto(
            fertilizer.getId(),
            fertilizer.getName(),
            fertilizer.getBrand(),
            fertilizer.getComposition()
    );

    return ResponseEntity.ok(fertilizerByIdDto);
  }
}

