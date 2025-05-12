package com.example.internal_bank_dms.repository;

import com.example.internal_bank_dms.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DocumentRepository extends JpaRepository<Document,Long> {

    void deleteByBucketNameAndKey(String bucketName, String key);

    Optional<Document> findByKey(String key);
}
