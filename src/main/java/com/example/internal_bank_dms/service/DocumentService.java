package com.example.internal_bank_dms.service;

import com.example.internal_bank_dms.entity.Document;
import com.example.internal_bank_dms.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;

@Service
public class DocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    public ResponseEntity<String> uploadFile(Document document)
    {
        documentRepository.save(document);
        return new ResponseEntity<>("successfully added", HttpStatus.CREATED);
    }

}
