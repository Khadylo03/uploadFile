package org.sonatel.uploadfile.service.interfaces;

import org.sonatel.uploadfile.domain.enums.StorageType;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

public interface IStorageStrategy {
    Object saveFile(MultipartFile file) throws IOException;
    StorageType getStorageType();
}
