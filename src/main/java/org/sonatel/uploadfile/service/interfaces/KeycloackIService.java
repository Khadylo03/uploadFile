package org.sonatel.uploadfile.service.interfaces;

import jakarta.ws.rs.core.Response;
import org.sonatel.uploadfile.model.dto.request.UserRequestDTO;


public interface KeycloackIService {
    Response login(UserRequestDTO userRequest);

   // Response createUser(UserRepresentation userRepresentation);
   // Response getRoleUser(String id, String userId);

    }
