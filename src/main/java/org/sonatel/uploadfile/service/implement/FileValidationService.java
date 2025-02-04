package org.sonatel.uploadfile.service.implement;

import org.sonatel.uploadfile.domain.entity.FileEntity;
import org.sonatel.uploadfile.service.interfaces.IFileValidation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileValidationService implements IFileValidation {

     @Value("${acceptedTypes.type}")
     private String acceptedTypes;

    // Méthode pour valider le type de fichier
    @Override
    public boolean ValidTypeFile(FileEntity fileEntity) {

         List<String> accepted = List.of(acceptedTypes.split(","));
        System.out.println("OK");
        return accepted.contains(fileEntity.getFiletype());
}

    private long maxSizeFile(){
        int maxSize =  10 * 1024 * 1024;  // 10 Mo
        return maxSize;
    }
    // Méthode pour valider la taille du fichier
    @Override
    public boolean ValidSizeFile(FileEntity fileEntity) {
        // Vérifier si la taille du fichier est inférieure ou égale à la taille maximale
        return fileEntity.getFilesize() <= maxSizeFile();
    }

}
