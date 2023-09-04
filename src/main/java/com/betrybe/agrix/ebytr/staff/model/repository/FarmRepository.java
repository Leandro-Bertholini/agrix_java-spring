package com.betrybe.agrix.ebytr.staff.model.repository;

import com.betrybe.agrix.ebytr.staff.model.entity.Farm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Farm JPA repository.
 */
@Repository
public interface FarmRepository extends JpaRepository<Farm, Integer> {
}

