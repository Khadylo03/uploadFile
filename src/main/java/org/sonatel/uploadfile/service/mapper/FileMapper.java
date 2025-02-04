package org.sonatel.uploadfile.service.mapper;

import org.mapstruct.Mapper;
import org.sonatel.uploadfile.model.dto.request.FileRequestDTO;
import org.sonatel.uploadfile.model.dto.response.FileResponse;
import org.sonatel.uploadfile.domain.entity.FileEntity;

@Mapper
public interface FileMapper {

    //transformer un objet de type FileRequest en un objet de type FileEntity
    FileEntity toEntity(FileRequestDTO request);

    //transformer un objet de type FileEntity en un objet de type FileRequest
    FileRequestDTO toDto(FileEntity fileEntity);

    // Mapping File -> FileResponse
    FileResponse toResponse(FileEntity fileEntity);

}
