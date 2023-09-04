package com.betrybe.agrix.ebytr.staff.model.repository;

import com.betrybe.agrix.ebytr.staff.model.entity.Fertilizer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Fertilizer JPA repository.
 */

@Repository
public interface FertilizerRepository extends JpaRepository<Fertilizer, Integer> {
}

