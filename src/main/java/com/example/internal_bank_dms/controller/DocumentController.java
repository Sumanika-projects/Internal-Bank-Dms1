package com.example.internal_bank_dms.controller;

import com.example.internal_bank_dms.entity.Document;
import com.example.internal_bank_dms.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.io.IOException;

@RestController
@RequestMapping("/doc")
public class DocumentController {

    @Autowired
    private S3Client s3Client;

    @Autowired
    private DocumentService documentService;

    @Value("${aws.bucket}")
    private String bucketName;


    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        String key = file.getOriginalFilename();
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build();
        s3Client.putObject(putObjectRequest, RequestBody.fromBytes(file.getBytes()));
        Document document = new Document();
        document.setKey(key);
        document.setBucketName(bucketName);

        return documentService.uploadFile(document);


    }

    @GetMapping("/download")
    public ResponseEntity<byte[]> getFile(@RequestParam String bucketName, @RequestParam String key)
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
}

