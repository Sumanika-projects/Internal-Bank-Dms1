package com.example.internal_bank_dms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.services.s3.model.S3Exception;

import java.io.IOException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AwsServiceException.class)
    public ResponseEntity<String> handleAwsService(AwsServiceException e)
    {
        return new ResponseEntity<>("Aws error " + e.awsErrorDetails().errorMessage(), HttpStatusCode.valueOf(e.statusCode()));

    }
    @ExceptionHandler(S3Exception.class)
    public ResponseEntity<String> handleS3(S3Exception e) // used for server side - wrong credentials ,
    {
        return new ResponseEntity<>("S3 error " + e.awsErrorDetails().errorMessage(), HttpStatusCode.valueOf(e.statusCode()));

    }

    @ExceptionHandler(SdkClientException.class) // used for client side errors - wrong region/timeout,internet issue
    public ResponseEntity<String> handleS3(SdkClientException e)
    {
        return new ResponseEntity<>("Sdk error " + e.getMessage(), HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<String> handleS3(IOException e)
    {
        return new ResponseEntity<>("I/O error " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleAnyException(Exception e)
    {
        return ResponseEntity.internalServerError().body(e.getMessage());
    }

}
