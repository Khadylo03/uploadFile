package org.sonatel.uploadfile.domain.entity;

import jakarta.persistence.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

@Entity
public class FileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String filename;
    private String filetype;
    private Long filesize;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createtime;

    @Lob
    private byte[] filedata;

    // Constructeurs
    public FileEntity() {

    }

    public FileEntity(String filename, String filetype, Long filesize, Date createtime, byte[] filedata) {
        this.filename = filename;
        this.filetype = filetype;
        this.filesize = filesize;
        this.createtime = createtime;
        this.filedata = filedata;
    }

    // Constructeur qui crée un FileEntity à partir d'un MultipartFile
    public FileEntity(MultipartFile file) throws IOException {
        this.filename = file.getOriginalFilename();
        this.filetype = file.getContentType();
        this.filesize = file.getSize();
        this.createtime = new Date();
        this.filedata = file.getBytes();
    }


    // Getters et Setters
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

    public byte[] getFiledata() {
        return filedata;
    }

    public void setFiledata(byte[] filedata) {
        this.filedata = filedata;
    }
}
