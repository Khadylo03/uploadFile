package org.sonatel.uploadfile.service.implement;

import org.sonatel.uploadfile.domain.entity.FileEntity;
import org.sonatel.uploadfile.service.interfaces.IFileValidation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;
@Service
public class FileValidationService implements IFileValidation {

    private static final Logger logger = Logger.getLogger(FileValidationService.class.getName());
//    private static final String acceptedTypes =  "image/jpeg,image/png,application/pdf"; // Exemple de types acceptés
    @Value("${acceptedTypes}")
    private String acceptedTypes;

    @Override
    public boolean ValidTypeFile(FileEntity fileEntity) {
        List<String> accepted = List.of(acceptedTypes.toLowerCase().split(","));
        String fileType = fileEntity.getFiletype();

        System.out.println("Type de fichier reçu : " + fileType);
        System.out.println("Types acceptés : " + accepted);

        if (fileType == null || !accepted.contains(fileType.toLowerCase())) {
          return false;
        }
        return true;
    }

    private long maxSizeFile() {
        return 10 * 1024 * 1024;  // 10 Mo
    }

    @Override
    public boolean ValidSizeFile(FileEntity fileEntity) {
        // Vérifier si la taille du fichier est inférieure ou égale à la taille maximale
        return fileEntity.getFilesize() <= maxSizeFile();
    }


//    @Override
//    public boolean ValidSizeFile(FileEntity fileEntity) {
//        try {
//            long fileSize = fileEntity.getFilesize();
//
//            if (fileSize > maxSizeFile()) {
//                logger.warning("Fichier trop volumineux : " + fileSize + " octets.");
//                return false;
//            }
//
//            logger.info("Taille de fichier acceptée : " + fileSize + " octets.");
//            return true;
//
//        } catch (Exception e) {
//            logger.severe("Erreur lors de la validation de la taille du fichier : " + e.getMessage());
//            return false;
//        }
//    }
}
