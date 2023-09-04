package com.betrybe.agrix.ebytr.staff.controller;

import com.betrybe.agrix.ebytr.staff.controller.dto.CropDto;
import com.betrybe.agrix.ebytr.staff.controller.dto.FarmDto;
import com.betrybe.agrix.ebytr.staff.model.entity.Crop;
import com.betrybe.agrix.ebytr.staff.model.entity.Farm;
import com.betrybe.agrix.ebytr.staff.service.CropService;
import com.betrybe.agrix.ebytr.staff.service.FarmService;
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
 * Classe Controller apondada para fazenda.
 */

@RestController
@RequestMapping("/farms")
public class FarmController {
  private final FarmService farmService;
  private final CropService cropService;

  /**
   * Construtor.
   */
  public FarmController(FarmService farmService, CropService cropService) {
    this.farmService = farmService;
    this.cropService = cropService;
  }

  /**
   * Criação da entidade fazenda (farm).
   *
   * @param farmDto - Objeto de transferência de dados para requisição.
   * @return - O estatuto com o objeto fazenda.
   */

  @PostMapping
  public ResponseEntity<Farm> createFarm(@RequestBody FarmDto farmDto) {
    Farm createdFarm = farmService.createFarm(farmDto.toFarm());

    return ResponseEntity.status(HttpStatus.CREATED).body(createdFarm);
  }

  /**
   * Capturar todas as fazendas cadastradas.
   */
  @GetMapping
  public List<FarmDto> getAllFarm() {
    List<Farm> allFarms = farmService.getAllFarms();
    return allFarms.stream()
            .map((farm) -> new FarmDto(farm.getId(), farm.getName(), farm.getSize()))
            .collect(Collectors.toList());
  }

  /**
   * Capturar a fazenda cadastrada por "ID".
   */
  @GetMapping("/{id}")
  public ResponseEntity<Farm> getFarmById(@PathVariable Integer id) {
    Farm farmById = farmService.getFarmById(id);
    return ResponseEntity.ok(farmById);
  }

  /**
   * Relacionamento de "farm" e "crop" - Cria uma plantação a partir do "ID" da fazenda.
   */

  @PostMapping("/{farmId}/crops")
  public ResponseEntity<CropDto> createCrop(@PathVariable Integer farmId, @RequestBody Crop crop) {
    Farm farm = farmService.getFarmById(farmId);
    Crop newCrop = cropService.createCrop(farm, crop);

    CropDto newCropDto = new CropDto(
            newCrop.getId(),
            newCrop.getName(),
            newCrop.getPlantedArea(),
            newCrop.getPlantedDate(),
            newCrop.getHarvestDate(),
            farm.getId()
    );

    return ResponseEntity.status(HttpStatus.CREATED).body(newCropDto);
  }

  /**
   * Relacionamento de "farm" e "crop" - Lista todas as plantações a partir do "ID" da fazenda.
   */

  @GetMapping("/{farmId}/crops")
  public ResponseEntity<List<CropDto>> getCropByFarmId(@PathVariable Integer farmId) {
    Farm farm = farmService.getFarmById(farmId);
    List<CropDto> allCropByFarmId = farm.getCrops().stream()
            .map((crop) -> new CropDto(
                    crop.getId(),
                    crop.getName(),
                    crop.getPlantedArea(),
                    crop.getPlantedDate(),
                    crop.getHarvestDate(),
                    crop.getFarm().getId()
            ))
            .collect(Collectors.toList());

    return ResponseEntity.ok(allCropByFarmId);
  }
}


