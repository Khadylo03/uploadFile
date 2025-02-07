package org.sonatel.uploadfile.web;

import org.sonatel.uploadfile.domain.entity.FileEntity;
import org.sonatel.uploadfile.model.dto.request.FileRequestDTO;
import org.sonatel.uploadfile.service.FileService;
import org.sonatel.uploadfile.service.mapper.FileMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/files")
public class FileOperationCRUDcontroller {
    private final FileService fileService;
    public FileOperationCRUDcontroller(FileService fileService) {
        this.fileService = fileService;
    }



    @GetMapping
    public ResponseEntity<List<FileRequestDTO>> getAllFiles() {
        List<FileRequestDTO> fileDTOs = fileService.getAllFiles()
                .stream()
                .map(FileMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(fileDTOs);
    }

//    @GetMapping
//    public ResponseEntity<List<FileEntity>> getAllFiles() {
//        List<FileEntity> files = fileService.getAllFiles();
//        return ResponseEntity.ok(files);
//    }

    /**
     * Endpoint pour récupérer un fichier par ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getFileById(@PathVariable int id) {
        Optional<FileEntity> file = fileService.getFileById(id);
        if (file.isPresent()) {
            return ResponseEntity.ok(file.get());
        }
        return ResponseEntity.notFound().build();
    }

    // Endpoint pour supprimer un fichier par son ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteFile(@PathVariable int id) {
        try {
            fileService.deleteFileById(id);
            return ResponseEntity.ok("Fichier supprimé avec succès.");
        } catch (Exception e) {
            return ResponseEntity.status(404).body("Fichier non trouvé.");
        }
    }
}
