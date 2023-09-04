package com.betrybe.agrix.ebytr.staff.service;

import com.betrybe.agrix.ebytr.staff.exception.CropFindByIdErrorException;
import com.betrybe.agrix.ebytr.staff.exception.CropsFindAllErrorException;
import com.betrybe.agrix.ebytr.staff.model.entity.Crop;
import com.betrybe.agrix.ebytr.staff.model.entity.Farm;
import com.betrybe.agrix.ebytr.staff.model.entity.Fertilizer;
import com.betrybe.agrix.ebytr.staff.model.repository.CropRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Serviço apontado para a entidade plantação.
 */
@Service
public class CropService {

  private final CropRepository cropRepository;
  private final FertilizerService fertilizerService;

  @Autowired
  public CropService(
          CropRepository cropRepository,
          FertilizerService fertilizerService
  ) {
    this.cropRepository = cropRepository;
    this.fertilizerService = fertilizerService;
  }

  /**
   * Inserir plantação a partir de um "ID" da fazenda.
   */
  public Crop createCrop(Farm farm, Crop crop) {
    crop.setFarm(farm);
    Crop newCrop = cropRepository.save(crop);
    return newCrop;
  }

  /**
   * Listar todas as Plantações cadastradas.
   */
  public List<Crop> getAllCrops() throws CropsFindAllErrorException {
    try {
      List<Crop> allCrops = cropRepository.findAll();
      return allCrops;
    } catch (Exception e) {
      throw new CropsFindAllErrorException();
    }
  }

  /**
   * Capturar a plantação pelo "ID".
   */
  public Crop getCropById(Integer id) throws CropFindByIdErrorException {
    Optional<Crop> cropById = cropRepository.findById(id);

    if (cropById.isEmpty()) {
      throw new CropFindByIdErrorException();
    }

    return cropById.get();
  }

  /**
   * Busca de plantações a partir da data de colheita.
   * Método com criação de consulta do JPA.
   */

  public List<Crop> getAllCropByHarvestDate(
          LocalDate start,
          LocalDate end
  ) throws CropsFindAllErrorException {

    try {
      List<Crop> allCropsByHarvestDate = cropRepository.findByHarvestDateBetween(start, end);
      return allCropsByHarvestDate;
    } catch (Exception e) {
      throw new CropsFindAllErrorException();
    }
  }

  /**
   * Associa plantação com o fertilizante.
   */
  public Crop associateCropWithFertilizer(Integer cropId, Integer fertilizerId) {
    Crop crop = getCropById(cropId);
    Fertilizer fertilizer = fertilizerService.getFertilizerById(fertilizerId);

    // Usamos "add" com a lista de fertilizantes para não substituir a lista existente usando "set"
    crop.getFertilizers().add(fertilizer);
    Crop cropWithFertilizer = cropRepository.save(crop);
    return cropWithFertilizer;

  }
}


