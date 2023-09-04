package com.betrybe.agrix.ebytr.staff.advice;

import com.betrybe.agrix.ebytr.staff.exception.FarmFindAllErrorException;
import com.betrybe.agrix.ebytr.staff.exception.FarmFindByIdErrorException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * Classe para tratamento de exceções apontada para fazendas(farm).
 */

@RestControllerAdvice
public class FarmControllerAdvice {


  /**
   * Lida com a exceção para "findAll".
   */
  @ExceptionHandler({
    FarmFindAllErrorException.class
  })
  public ResponseEntity<Map<String, String>> handleFindAllFarmException(
          FarmFindAllErrorException e
  ) {
    Map<String, String> errorResponse = new HashMap<>();
    errorResponse.put("message", "Erro ao retornar as fazendas cadastradas.");
    return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(errorResponse);
  }

  /**
   * Lida com a exceção para "findById".
   */
  @ExceptionHandler({
    FarmFindByIdErrorException.class
  })
  public ResponseEntity<Map<String, String>> handleFindByIdFarmException(
          FarmFindByIdErrorException e
  ) {
    Map<String, String> errorResponse = new HashMap<>();
    errorResponse.put("message", "Fazenda não encontrada!");
    return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(errorResponse);
  }
}


