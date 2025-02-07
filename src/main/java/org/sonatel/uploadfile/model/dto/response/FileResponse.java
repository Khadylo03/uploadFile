package org.sonatel.uploadfile.model.dto.response;

import jakarta.persistence.Lob;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.util.Date;

public class FileResponse {
    private int id;
    private String filename;
    private String filetype;
    private Long filesize;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createtime;
//
//    @Lob
//    private byte[] filedata;
}
