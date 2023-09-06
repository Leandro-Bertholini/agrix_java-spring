package com.betrybe.agrix.ebytr.staff.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


/**
 * Serviço para o token.
 */

@Service
public class TokenService {

  @Value("${api.security.token.secret}")
  private String secret;

  /**
   * Geração do token.
   */
  public String generateToken(UserDetails userDetails) {
    Algorithm algorithm = Algorithm.HMAC256(secret);
    return JWT.create()
            .withIssuer("agrix")
            .withSubject(userDetails.getUsername())
            .withExpiresAt(generateExpirationDate())
            .sign(algorithm);
  }

  /**
   * Define a expiração do token.
   */
  private Instant generateExpirationDate() {
    return LocalDateTime.now().plusDays(3).toInstant(ZoneOffset.of("-03:00"));
  }

  /**
   * Validação do token.
   */
  public String validateToken(String token) {
    Algorithm algorithm = Algorithm.HMAC256(secret);
    return JWT.require(algorithm)
            .withIssuer("agrix")
            .build()
            .verify(token)
            .getSubject();
  }
}
