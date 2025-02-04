package org.sonatel.uploadfile.service.storage;

import org.sonatel.uploadfile.domain.enums.StorageType;
import org.sonatel.uploadfile.service.interfaces.IFileValidation;
import org.sonatel.uploadfile.domain.entity.FileEntity;
import org.sonatel.uploadfile.repository.FileRepository;
import org.sonatel.uploadfile.service.interfaces.IStorageStrategy;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

@Service("databaseStorage")
public class DatabaseStorageStrategy implements IStorageStrategy {
    private final FileRepository fileRepository;
    private final IFileValidation fileValidationService;

    public DatabaseStorageStrategy(FileRepository fileRepository, IFileValidation fileValidationService) {
        this.fileRepository = fileRepository;
        this.fileValidationService = fileValidationService;
    }

    @Override
    public FileEntity saveFile(MultipartFile file) throws IOException {
        // Vérification si le fichier n'est pas vide
//        if (file.isEmpty()) {
//            throw new IllegalArgumentException("Le fichier est vide.");
//        }

        // Vérifier le type de fichier
        if (!fileValidationService.ValidTypeFile(new FileEntity(file))) {
            throw new IllegalArgumentException("Type de fichier non valide");
        }

        // Vérifier la taille du fichier
        if (!fileValidationService.ValidSizeFile(new FileEntity(file))) {
            throw new IllegalArgumentException("Fichier trop volumineux");
        }

        // Création de l'entité FileEntity
        FileEntity fileEntity = new FileEntity(
                file.getOriginalFilename(),
                file.getContentType(),
                file.getSize(),
                new Date(),
                file.getBytes()
        );


        // Enregistrement dans la base
        return fileRepository.save(fileEntity);
    }
    public StorageType getStorageType(){
        return StorageType.DATABASE;
    }

}

