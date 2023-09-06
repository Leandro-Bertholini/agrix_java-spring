package com.betrybe.agrix.ebytr.staff.controller;

import com.betrybe.agrix.ebytr.staff.controller.dto.AuthDto;
import com.betrybe.agrix.ebytr.staff.controller.dto.LoginResponseDto;
import com.betrybe.agrix.ebytr.staff.security.TokenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * AuthenticationController.
 */
@RestController
@RequestMapping("/auth")
public class AuthController {
  private final AuthenticationManager authenticationManager;
  private final TokenService tokenService;

  public AuthController(AuthenticationManager authenticationManager, TokenService tokenService) {
    this.authenticationManager = authenticationManager;
    this.tokenService = tokenService;
  }

  /**
   * Registrar usuário através do login.
   */
  @PostMapping("/login")
  public ResponseEntity<LoginResponseDto> login(@RequestBody AuthDto authDto) {
    UsernamePasswordAuthenticationToken loginData = new UsernamePasswordAuthenticationToken(
            authDto.username(),
            authDto.password()
    );
    Authentication authDone = authenticationManager.authenticate(loginData);

    UserDetails userDetails = (UserDetails) authDone.getPrincipal();

    String token = tokenService.generateToken(userDetails);
    LoginResponseDto responseToken = new LoginResponseDto(token);

    return ResponseEntity.status(HttpStatus.OK).body(responseToken);

  }
}
