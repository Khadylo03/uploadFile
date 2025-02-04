package org.sonatel.uploadfile.service.mapper;

import org.mapstruct.Mapper;
import org.sonatel.uploadfile.model.dto.request.UserRequestDTO;
import org.sonatel.uploadfile.model.dto.response.UserResponse;
import org.sonatel.uploadfile.domain.entity.UserEntity;

@Mapper
public interface UserMapper {
    // Mapping CreateUserRequest -> User
    UserEntity toEntity(UserRequestDTO request);

    // Mapping User -> UserResponse
    UserResponse toResponse(UserEntity user);

}
