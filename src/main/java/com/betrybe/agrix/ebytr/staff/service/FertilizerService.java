package com.betrybe.agrix.ebytr.staff.service;

import com.betrybe.agrix.ebytr.staff.exception.FertilizerFindByIdErrorException;
import com.betrybe.agrix.ebytr.staff.model.entity.Fertilizer;
import com.betrybe.agrix.ebytr.staff.model.repository.FertilizerRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Serviço apontado para a entidade fertilizante.
 */

@Service
public class FertilizerService {

  private final FertilizerRepository fertilizerRepository;

  @Autowired
  public FertilizerService(FertilizerRepository fertilizerRepository) {
    this.fertilizerRepository = fertilizerRepository;
  }

  /**
   * Inserir nova entidade fertilizante(Fertilizer).
   */
  public Fertilizer createFertilizer(Fertilizer fertilizer) {
    Fertilizer newFertilizer = fertilizerRepository.save(fertilizer);
    return newFertilizer;
  }

  /**
   * Capturar todas os fertililzantes cadastrados.
   */
  public List<Fertilizer> getAllFertilizer() {
    List<Fertilizer> allFertilizers = fertilizerRepository.findAll();
    return allFertilizers;
  }

  /**
   * Capturar o fertilizante pelo ID.
   */
  public Fertilizer getFertilizerById(Integer id) throws FertilizerFindByIdErrorException {
    Optional<Fertilizer> fertilizerById = fertilizerRepository.findById(id);

    if (fertilizerById.isEmpty()) {
      throw new FertilizerFindByIdErrorException();
    }

    return fertilizerById.get();
  }
}

