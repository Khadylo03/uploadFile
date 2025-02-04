package org.sonatel.uploadfile.web;

import org.sonatel.uploadfile.domain.entity.FileEntity;
import org.sonatel.uploadfile.service.FilePaginationService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
public class FilePaginationController {

    private final FilePaginationService filePaginationService;

    public FilePaginationController(FilePaginationService filePaginationService) {
        this.filePaginationService = filePaginationService;
    }

    @GetMapping("/ALLfiles")
    public Page<FileEntity> getFiles(@RequestParam int page, @RequestParam int size) {
        return filePaginationService.getFiles(page, size);
    }
}
