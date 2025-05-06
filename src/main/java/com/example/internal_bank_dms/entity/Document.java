package com.example.internal_bank_dms.entity;

import jakarta.persistence.*;

@Entity
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false)
    private Long id;

    @Column(name= "Bucket_name")
    private String BucketName;
    private String Key;

    public Document() {
    }

    public Document(Long id, String bucketName, String key) {
        this.id = id;
        BucketName = bucketName;
        Key = key;
    }

    public Long getId() {
        return id;
    }

    public String getBucketName() {
        return BucketName;
    }

    public String getKey() {
        return Key;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setBucketName(String bucketName) {
        BucketName = bucketName;
    }

    public void setKey(String key) {
        Key = key;
    }

    @Override
    public String toString() {
        return "Document{" +
                "id=" + id +
                ", BucketName='" + BucketName + '\'' +
                ", Key='" + Key + '\'' +
                '}';
    }
}
