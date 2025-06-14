package com.example.internal_bank_dms.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "document_extension")
public class DocumentExtension {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name ="extension_name",nullable = false,unique = true)
    private String extensionName;
    @Column
    private String key;

    public DocumentExtension(Long id, String extensionName, String key) {
        this.id = id;
        this.extensionName = extensionName;
        this.key = key;
    }

    public Long getId() {
        return id;
    }

    public String getExtensionName() {
        return extensionName;
    }

    public String getKey() {
        return key;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setExtensionName(String extensionName) {
        this.extensionName = extensionName;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
