package com.example.internal_bank_dms.repository;

import com.example.internal_bank_dms.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends JpaRepository<Document,Long> {

    public void deleteByBucketNameAndKey(String bucketName, String key);
}
