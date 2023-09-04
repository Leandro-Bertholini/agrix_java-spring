package com.betrybe.agrix.ebytr.staff.controller;

import com.betrybe.agrix.ebytr.staff.controller.dto.CropDto;
import com.betrybe.agrix.ebytr.staff.controller.dto.FertilizerDto;
import com.betrybe.agrix.ebytr.staff.model.entity.Crop;
import com.betrybe.agrix.ebytr.staff.service.CropService;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * Classe Controller apondada para plantação.
 */

@RestController
@RequestMapping("/crops")
public class CropController {

  private final CropService cropService;

  /**
   * Construtor.
   */
  public CropController(CropService cropService) {
    this.cropService = cropService;
  }

  /**
   * Capturar todas as plantações cadastradas.
   */
  @GetMapping
  public ResponseEntity<List<CropDto>> getAllCrop() {
    List<Crop> allCrops = cropService.getAllCrops();

    List<CropDto> allCropDtos =  allCrops.stream()
            .map((crop) -> new CropDto(
                    crop.getId(),
                    crop.getName(),
                    crop.getPlantedArea(),
                    crop.getPlantedDate(),
                    crop.getHarvestDate(),
                    crop.getFarm().getId()))
            .collect(Collectors.toList());

    return ResponseEntity.ok(allCropDtos);
  }

  /**
   * Capturar a fazenda cadastrada por "ID".
   */
  @GetMapping("/{id}")
  public ResponseEntity<CropDto> getCropById(@PathVariable Integer id) {
    Crop cropById = cropService.getCropById(id);
    CropDto cropByIdDto = new CropDto(
            cropById.getId(),
            cropById.getName(),
            cropById.getPlantedArea(),
            cropById.getPlantedDate(),
            cropById.getHarvestDate(),
            cropById.getFarm().getId()
    );
    return ResponseEntity.ok(cropByIdDto);
  }

  /**
   * Capturar todas as plantações a partir de uma linha do tempo.
   */
  @GetMapping("/search")
  public ResponseEntity<List<CropDto>> getAllCropByHarvestDate(
          @RequestParam LocalDate start,
          @RequestParam LocalDate end
  ) {
    List<Crop> allCropsByHarvestDate = cropService.getAllCropByHarvestDate(start, end);

    List<CropDto> cropDtosByHarvestDate =  allCropsByHarvestDate.stream()
            .map((crop) -> new CropDto(
                    crop.getId(),
                    crop.getName(),
                    crop.getPlantedArea(),
                    crop.getPlantedDate(),
                    crop.getHarvestDate(),
                    crop.getFarm().getId()))
            .collect(Collectors.toList());

    return ResponseEntity.ok(cropDtosByHarvestDate);
  }

  /**
   * Associa plantação com o fertilizante.
   */
  @PostMapping("/{cropId}/fertilizers/{fertilizerId}")
  public ResponseEntity<String> associateCropWithFertilizer(
          @PathVariable Integer cropId,
          @PathVariable Integer fertilizerId
  ) {
    cropService.associateCropWithFertilizer(cropId, fertilizerId);
    return ResponseEntity.status(HttpStatus.CREATED)
            .body("Fertilizante e plantação associados com sucesso!");
  }

  /**
   * Lista todos os fertilizantes a partir do "ID" da plantação.
   */
  @GetMapping("/{cropId}/fertilizers")
  public ResponseEntity<List<FertilizerDto>> getFertilizersByCropId(@PathVariable Integer cropId) {
    Crop cropById = cropService.getCropById(cropId);

    List<FertilizerDto> fertilizersDto = cropById.getFertilizers().stream()
            .map(fertilizer -> new FertilizerDto(
                    fertilizer.getId(),
                    fertilizer.getName(),
                    fertilizer.getBrand(),
                    fertilizer.getComposition()
            ))
            .collect(Collectors.toList());

    return ResponseEntity.ok(fertilizersDto);
  }
}

