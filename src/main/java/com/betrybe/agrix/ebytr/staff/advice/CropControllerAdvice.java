package com.betrybe.agrix.ebytr.staff.advice;

import com.betrybe.agrix.ebytr.staff.exception.CropFindByIdErrorException;
import com.betrybe.agrix.ebytr.staff.exception.CropsFindAllErrorException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Classe para tratamento de exceções apontada para plantação(crop).
 */

@RestControllerAdvice
public class CropControllerAdvice {

  /**
   * Lida com a exceção para "findAll".
   */
  @ExceptionHandler({CropsFindAllErrorException.class})
  public ResponseEntity<Map<String, String>> handleFindAllFarmException(
          CropsFindAllErrorException e
  ) {
    Map<String, String> errorResponse = new HashMap<>();
    errorResponse.put("message", "Erro ao retornar as plantações cadastradas.");
    return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(errorResponse);
  }

  /**
   * Lida com a exceção para "findById".
   */
  @ExceptionHandler({
    CropFindByIdErrorException.class
  })
  public ResponseEntity<Map<String, String>> handleFindByIdFarmException(
          CropFindByIdErrorException e
  ) {
    Map<String, String> errorResponse = new HashMap<>();
    errorResponse.put("message", "Plantação não encontrada!");
    return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(errorResponse);
  }
}


