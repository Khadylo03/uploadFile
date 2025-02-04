package org.sonatel.uploadfile.service;

import org.sonatel.uploadfile.domain.entity.FileEntity;
import org.sonatel.uploadfile.repository.FilePaginationRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class FilePaginationService {

    private final FilePaginationRepository filePaginationRepository;

    public FilePaginationService(FilePaginationRepository filePaginationRepository) {
        this.filePaginationRepository = filePaginationRepository;
    }


    public Page<FileEntity> getFiles(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);  // Page indexée à partir de 0
        return filePaginationRepository.findAll(pageable);
    }
}
