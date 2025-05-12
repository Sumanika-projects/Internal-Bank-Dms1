package com.example.internal_bank_dms.service;

import com.example.internal_bank_dms.entity.Document;
import com.example.internal_bank_dms.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.util.Optional;

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

    public ResponseEntity<String> updateFile(String bucketName,String key,MultipartFile file) throws IOException {
        Optional<Document> optionalData = documentRepository.findByKey(key);

        if (optionalData.isEmpty())
            throw new RuntimeException("File not found in db");
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .contentType(file.getContentType())
                .build();

        s3Client.putObject(putObjectRequest, RequestBody.fromBytes(file.getBytes()));
        return new ResponseEntity<>("Successfully updated",HttpStatus.OK);
    }

}
