package com.betrybe.agrix.ebytr.staff.model.repository;

import com.betrybe.agrix.ebytr.staff.model.entity.Crop;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



/**
 * Crop JPA repository.
 */
@Repository
public interface CropRepository extends JpaRepository<Crop, Integer> {

  /**
   * Lista as plantações cadastradas por datas de colheita.
   *
   * @param start - A partir.
   * @param end - Data limite
   * @return - Uma lista de plantações com a data da colheita
   */
  List<Crop> findByHarvestDateBetween(LocalDate start, LocalDate end);
}
