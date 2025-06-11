package com.example.internal_bank_dms.service;

import com.example.internal_bank_dms.entity.Document;
import com.example.internal_bank_dms.repository.DocumentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.io.IOException;
import java.util.Optional;

@Service
public class DocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private S3Client s3Client;

    public ResponseEntity<String> uploadFile(String bucketName,String key, MultipartFile file) throws IOException {
        try {
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(key)
                    .build();
            s3Client.putObject(putObjectRequest, RequestBody.fromBytes(file.getBytes()));
            Document document = new Document();
            document.setKey(key);
            document.setBucketName(bucketName);
            documentRepository.save(document);
            return new ResponseEntity<>("successfully added", HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("File upload failed ", HttpStatus.FORBIDDEN);
        }

    }

    public ResponseEntity<byte[]> downloadFile(String bucketName,String key)
    {
        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build();
        ResponseBytes<GetObjectResponse> objectBytes  = s3Client.getObjectAsBytes(getObjectRequest);
        byte[] content = objectBytes.asByteArray();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentDispositionFormData("attachment",key);
        httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<>(content,httpHeaders,HttpStatus.OK);
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
