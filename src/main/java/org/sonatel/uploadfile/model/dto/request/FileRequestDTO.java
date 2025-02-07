package org.sonatel.uploadfile.model.dto.request;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Date;

public class FileRequestDTO {
    private int id;
    private String filename;
    private String filetype;
    private Long filesize;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createtime;

    public FileRequestDTO() {
    }

    public FileRequestDTO(int id, String filename, String filetype, Long filesize, Date createtime) {
        this.id = id;
        this.filename = filename;
        this.filetype = filetype;
        this.filesize = filesize;
        this.createtime = createtime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFiletype() {
        return filetype;
    }

    public void setFiletype(String filetype) {
        this.filetype = filetype;
    }

    public Long getFilesize() {
        return filesize;
    }

    public void setFilesize(Long filesize) {
        this.filesize = filesize;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}
