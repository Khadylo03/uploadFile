package org.sonatel.uploadfile.web;

import io.swagger.v3.oas.annotations.Operation;
import org.sonatel.uploadfile.domain.enums.StorageType;
import org.sonatel.uploadfile.service.interfaces.IStorageStrategy;
import org.sonatel.uploadfile.service.storage.StorageStrategyFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/files")
public class FileController {


    private final StorageStrategyFactory storageStrategyFactory;

    public FileController(StorageStrategyFactory storageStrategyFactory) {
        this.storageStrategyFactory = storageStrategyFactory;
    }

    @PostMapping(path = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(
            summary = "Télécharger un fichier",
            description = "Permet de choisir un fichier depuis l'ordinateur et de l'enregistrer soit dans la base de données, soit localement."
    )
    public Object uploadFile(
            @RequestParam MultipartFile file,
            @RequestParam StorageType storageType
    ) throws IOException {

        IStorageStrategy strategy = storageStrategyFactory.getStrategy(storageType);

        System.out.println(strategy);

        if (strategy == null) {
            throw new IllegalArgumentException("Type de stockage invalide.");

        }
        System.out.println("1 "+strategy);


        return strategy.saveFile(file);
    }
}
