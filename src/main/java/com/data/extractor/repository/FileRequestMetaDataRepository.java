package com.data.extractor.repository;

import com.data.extractor.repository.dao.FileRequestMetadata;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRequestMetaDataRepository extends JpaRepository<FileRequestMetadata, Long> {
}
