package com.betrybe.agrix.ebytr.staff.advice;

import com.betrybe.agrix.ebytr.staff.exception.FertilizerFindByIdErrorException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * Classe para tratamento de exceções apontada para fertilizantes(fertilizer).
 */

@RestControllerAdvice
public class FertilizerControllerAdvice {

  /**
   * Lida com a exceção para "findById".
   */
  @ExceptionHandler({
    FertilizerFindByIdErrorException.class
  })
  public ResponseEntity<Map<String, String>> fertilizerFindByIdErrorException(
      FertilizerFindByIdErrorException e
  ) {
    Map<String, String> errorResponse = new HashMap<>();
    errorResponse.put("message", "Fertilizante não encontrado!");
    return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(errorResponse);
  }
}

