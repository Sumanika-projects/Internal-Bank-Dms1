package com.example.internal_bank_dms.repository;

import com.example.internal_bank_dms.entity.DocumentExtension;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface DocumentExtensionRepo extends JpaRepository<DocumentExtension,Long> {

    @Query("SELECT d.key FROM DocumentExtension d WHERE d.extensionName = :extensionName")
    String findByExtensionName(@Param("extensionName") String extensionName);
}
