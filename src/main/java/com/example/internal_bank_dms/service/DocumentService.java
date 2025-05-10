package com.example.internal_bank_dms.service;

import com.example.internal_bank_dms.entity.Document;
import com.example.internal_bank_dms.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;

@Service
public class DocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private S3Client s3Client;

    public ResponseEntity<String> uploadFile(Document document)
    {
        documentRepository.save(document);
        return new ResponseEntity<>("successfully added", HttpStatus.CREATED);
    }
    @Transactional
    public ResponseEntity<String> deleteFile(String bucketName, String key)
    {
        DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
                                                    .bucket(bucketName)
                                                    .key(key)
                                                    .build();
        s3Client.deleteObject(deleteObjectRequest);
        documentRepository.deleteByBucketNameAndKey(bucketName,key);

        return new ResponseEntity<>("successfully deleted", HttpStatus.OK);
    }

}
