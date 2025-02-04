package org.sonatel.uploadfile.service.implement;

import jakarta.ws.rs.core.Response;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.representations.AccessTokenResponse;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.sonatel.uploadfile.model.dto.request.UserRequestDTO;
import org.sonatel.uploadfile.service.interfaces.KeycloackIService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class KeycloackService implements KeycloackIService {

    @Value("${keycloak.server-url}")
    private String keycloakAuthServerUrl;

    @Value("${keycloak.realm}")
    private String realm;

    @Value("${keycloak.client_id}")
    private String clientId;

    /**
     * Authentifie un utilisateur avec Keycloak et retourne un token d'accès.
     */
    public Response login(UserRequestDTO userRequest) {
        if (userRequest == null || userRequest.getUserName() == null || userRequest.getPassword() == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Username and password are required").build();
        }

        try {
            Keycloak userKeycloak = keycloackCredentials(userRequest);

            // Obtention du token d'accès
            AccessTokenResponse token = userKeycloak.tokenManager().getAccessToken();

            if (token != null) {
                return Response.ok().entity(token).build();
            } else {
                return Response.status(Response.Status.UNAUTHORIZED).entity("Invalid credentials").build();
            }
        } catch (Exception e) {
            System.err.println("Error during login: " + e.getMessage());
            e.printStackTrace();
            return Response.serverError().entity("Login failed: " + e.getMessage()).build();
        }
    }

    /**
     * Initialise une instance Keycloak avec les credentials de l'utilisateur.
     */
    private Keycloak keycloackCredentials(UserRequestDTO userRequest) {
        return KeycloakBuilder.builder()
                .serverUrl(keycloakAuthServerUrl)
                .realm(realm)
                .clientId(clientId)
                .grantType(OAuth2Constants.PASSWORD)
                .username(userRequest.getUserName())
                .password(userRequest.getPassword())
                .build();
    }

    /**
     * Crée une représentation des credentials utilisateur pour Keycloak.
     */
    private static CredentialRepresentation credentialRepresentation(String password) {
        CredentialRepresentation credential = new CredentialRepresentation();
        credential.setType(CredentialRepresentation.PASSWORD);
        credential.setValue(password);
        credential.setTemporary(false);
        return credential;
    }
}


