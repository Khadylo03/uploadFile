package org.sonatel.uploadfile.service.storage;

import org.sonatel.uploadfile.domain.entity.FileEntity;
import org.sonatel.uploadfile.domain.enums.StorageType;
import org.sonatel.uploadfile.service.interfaces.IFileValidation;
import org.sonatel.uploadfile.service.interfaces.IStorageStrategy;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service("localStorage")
public class LocalStorageStrategy implements IStorageStrategy {
    private final IFileValidation fileValidationService;

    public LocalStorageStrategy(IFileValidation fileValidationService) {
        this.fileValidationService = fileValidationService;
    }

    @Override
    public String saveFile(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("Le fichier est vide.");
        }
        // Vérifier le type de fichier
        if (!fileValidationService.ValidTypeFile(new FileEntity(file))) {
            throw new IllegalArgumentException("Type de fichier non valide");
        }

        // Vérifier la taille du fichier
        if (!fileValidationService.ValidSizeFile(new FileEntity(file))) {
            throw new IllegalArgumentException("Fichier trop volumineux");
        }
        String uploadDir = "src/main/resources/uploads/";

        Files.createDirectories(Paths.get(uploadDir));

        // Chemin complet du fichier
        String filePath = uploadDir + file.getOriginalFilename();

        // Écriture du fichier dans le répertoire
        try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
            outputStream.write(file.getBytes());
        }

        return filePath;
    }
public StorageType getStorageType() {
        return StorageType.LOCALE;
}

}


































//package org.sonatel.uploadfile.service.storage;
//
//import org.sonatel.uploadfile.domain.entity.FileEntity;
//import org.sonatel.uploadfile.domain.enums.StorageType;
//import org.sonatel.uploadfile.service.interfaces.IFileValidation;
//import org.sonatel.uploadfile.service.interfaces.IStorageStrategy;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//
//@Service("localStorage")
//public class LocalStorageStrategy implements IStorageStrategy {
//    private final IFileValidation fileValidationService;
//
//    public LocalStorageStrategy(IFileValidation fileValidationService) {
//        this.fileValidationService = fileValidationService;
//    }
//    @Override
//    public String saveFile(MultipartFile file) throws IOException {
//        if (file.isEmpty()) {
//            throw new IllegalArgumentException("Le fichier est vide.");
//        }
//
//        // Création d'un objet FileEntity valide
//        FileEntity fileEntity = new FileEntity();
//        fileEntity.setFilename(file.getOriginalFilename());
//        fileEntity.setFilesize(file.getSize());
//        fileEntity.setFiletype(file.getContentType());
//
//        // Vérifier le type de fichier
//        if (!fileValidationService.ValidTypeFile(fileEntity)) {
//            throw new IllegalArgumentException("Type de fichier non valide.");
//        }
//
//        // Vérifier la taille du fichier
//        if (!fileValidationService.ValidSizeFile(fileEntity)) {
//            throw new IllegalArgumentException("Fichier trop volumineux.");
//        }
//
//        String uploadDir = "src/main/resources/uploads/";
//        Path uploadPath = Paths.get(uploadDir);
//
//        // Créer le dossier s'il n'existe pas
//        if (!Files.exists(uploadPath)) {
//            Files.createDirectories(uploadPath);
//        }
//
//        // Chemin complet du fichier
//        String filePath = uploadDir + file.getOriginalFilename();
//
//        // Écriture du fichier dans le répertoire
//        try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
//            outputStream.write(file.getBytes());
//        }
//
//        return filePath;
//    }
//
//    @Override
//    public StorageType getStorageType() {
//        return StorageType.LOCALE;
//    }
//}

























