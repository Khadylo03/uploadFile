package org.sonatel.uploadfile.service;

import org.sonatel.uploadfile.domain.entity.FileEntity;
import org.sonatel.uploadfile.repository.FileRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FileService {
    private final FileRepository fileRepository;

    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    // Récupérer tous les fichiers
    public List<FileEntity> getAllFiles() {
        return fileRepository.findAll();
    }

    // Récupérer un fichier par ID
    public Optional<FileEntity> getFileById(int id) {
        return fileRepository.findById(id);
    }

    // Méthode pour supprimer un fichier par son ID
    public void deleteFileById(int id) {
        fileRepository.deleteById(id);
    }
}
