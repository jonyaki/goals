package com.jonathan.goals.controller;


import com.auth0.jwt.exceptions.JWTVerificationException;
import com.jonathan.goals.models.LoginRequest;
import com.jonathan.goals.services.LoginServices;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class UserController {
    @Autowired
    private LoginServices loginServices;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest, HttpServletResponse response) {
        loginServices.getLogin(loginRequest,response);
        return ResponseEntity.ok("El usuario se logeo correctamente.");
    }

    @GetMapping("/token-validate")
    public ResponseEntity<String> tokenValidate(HttpServletRequest request) {
        try {
            loginServices.verificateToken(request);
            return ResponseEntity.ok("Token válido");
        } catch (JWTVerificationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no válido: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al ejecutar el llamado " + e.getMessage());
        }
    }
}
