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

    public Document(Long id, String bucketName, String key) {
        this.id = id;
        this.bucketName = bucketName;
        this.key = key;
    }

    public Document() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "Document{" +
                "id=" + id +
                ", bucketName='" + bucketName + '\'' +
                ", key='" + key + '\'' +
                '}';
    }
}
