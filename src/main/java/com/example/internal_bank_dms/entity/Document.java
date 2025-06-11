package com.example.internal_bank_dms.entity;

import jakarta.persistence.*;

@Entity
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false)
    private Long id;

    @Column(name= "bucket_name")
    private String bucketName;

    @Column(name= "key")
    private String key;

    @Column(name = "file_name")
    private String filename;
    @Column
    private String uploadedBy;
    @Column
    private String uploadedAt;
    @Column
    private String updatedBy;
    @Column
    private String updatedAt;
    @Column
    private String fileContentType;

    public Long getId() {
        return id;
    }

    public String getBucketName() {
        return bucketName;
    }

    public String getKey() {
        return key;
    }

    public String getFilename() {
        return filename;
    }

    public String getUploadedBy() {
        return uploadedBy;
    }

    public String getUploadedAt() {
        return uploadedAt;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void setUploadedBy(String uploadedBy) {
        this.uploadedBy = uploadedBy;
    }

    public void setUploadedAt(String uploadedAt) {
        this.uploadedAt = uploadedAt;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getFileContentType() {
        return fileContentType;
    }

    public Document(Long id, String bucketName, String key, String filename, String uploadedBy, String uploadedAt, String updatedBy, String updatedAt, String fileContentType) {
        this.id = id;
        this.bucketName = bucketName;
        this.key = key;
        this.filename = filename;
        this.uploadedBy = uploadedBy;
        this.uploadedAt = uploadedAt;
        this.updatedBy = updatedBy;
        this.updatedAt = updatedAt;
        this.fileContentType = fileContentType;
    }

    public void setFileContentType(String fileContentType) {
        this.fileContentType = fileContentType;
    }

    public Document() {
    }
}
