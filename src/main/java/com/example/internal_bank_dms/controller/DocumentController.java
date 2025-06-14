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
        String fileName = file.getOriginalFilename();
        return documentService.uploadFile(bucketName,fileName,file);
    }

    @GetMapping("/download")
    public ResponseEntity<byte[]> getFile(@RequestParam String bucketName, @RequestParam String key)
    {
        return documentService.downloadFile(bucketName, key);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteFile(@RequestParam String bucketName, @RequestParam String key)
    {
        return documentService.deleteFile(bucketName,key);
    }

    @PostMapping("/update")
    public ResponseEntity<String> updateFile(@RequestParam("file") MultipartFile file) throws IOException {
        String key = file.getOriginalFilename();

        return documentService.updateFile(bucketName,key,file);

    }
}

