package com.betrybe.agrix.ebytr.staff.service;

import com.betrybe.agrix.ebytr.staff.exception.FarmFindAllErrorException;
import com.betrybe.agrix.ebytr.staff.exception.FarmFindByIdErrorException;
import com.betrybe.agrix.ebytr.staff.model.entity.Farm;
import com.betrybe.agrix.ebytr.staff.model.repository.FarmRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Serviço apontado para a entidade fazenda.
 */
@Service
public class FarmService {
  private final FarmRepository farmRepository;

  @Autowired
  public FarmService(FarmRepository farmRepository) {
    this.farmRepository = farmRepository;
  }

  /**
   * Inserir nova fazenda.
   *
   * @param farm - Dados do objeto fazenda.
   * @return - Um novo objeto fazenda criado.
   */
  public Farm createFarm(Farm farm) {
    return farmRepository.save(farm);
  }

  /**
   * Capturar todas as fazendas cadastradas.
   */
  public List<Farm> getAllFarms() throws FarmFindAllErrorException {
    try {
      List<Farm> allFarms = farmRepository.findAll();
      return allFarms;
    } catch (Exception e) {
      throw new FarmFindAllErrorException();
    }
  }

  /**
   * Capturar a fazenda pelo ID.
   */
  public Farm getFarmById(Integer id) throws FarmFindByIdErrorException {
    Optional<Farm> farmById = farmRepository.findById(id);

    if (farmById.isEmpty()) {
      throw new FarmFindByIdErrorException();
    }

    return farmById.get();
  }
}


