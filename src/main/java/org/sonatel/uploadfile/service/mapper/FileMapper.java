package org.sonatel.uploadfile.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.sonatel.uploadfile.model.dto.request.FileRequestDTO;
import org.sonatel.uploadfile.model.dto.response.FileResponse;
import org.sonatel.uploadfile.domain.entity.FileEntity;

@Mapper(componentModel = "spring")
public interface FileMapper {
    FileMapper INSTANCE = Mappers.getMapper(FileMapper.class);

    //transformer un objet de type FileRequest en un objet de type FileEntity
    FileEntity toEntity(FileRequestDTO request);

    //transformer un objet de type FileEntity en un objet de type FileRequest
    FileRequestDTO toDTO(FileEntity fileEntity);

    // Mapping File -> FileResponse
    FileResponse toResponse(FileEntity fileEntity);

}
