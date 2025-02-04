package org.sonatel.uploadfile.service.interfaces;

import org.sonatel.uploadfile.domain.entity.FileEntity;

public interface IFileValidation {


     boolean ValidTypeFile(FileEntity fileEntity);
     boolean ValidSizeFile(FileEntity fileEntity) ;


}
