package org.sonatel.uploadfile.web;

import jakarta.ws.rs.core.Response;
import org.sonatel.uploadfile.model.dto.request.UserRequestDTO;
import org.sonatel.uploadfile.service.interfaces.KeycloackIService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class KeycloakController {

    private final KeycloackIService keycloackService;

    public KeycloakController(KeycloackIService keycloackService) {
        this.keycloackService = keycloackService;
    }

    /**
     * Endpoint pour authentifier un utilisateur.
     *
     * @param userRequest Les informations de l'utilisateur (username, password).
     * @return ResponseEntity contenant le token d'accès ou un message d'erreur.
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserRequestDTO userRequest) {
        Response response = keycloackService.login(userRequest);

        if (response.getStatus() == Response.Status.OK.getStatusCode()) {
            return ResponseEntity.ok(response.getEntity());
        } else if (response.getStatus() == Response.Status.UNAUTHORIZED.getStatusCode()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response.getEntity());
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response.getEntity());
        }
    }
}
