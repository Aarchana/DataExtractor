package com.data.extractor.repository.dao;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "file_metadata")
public class FileRequestMetadata {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String requestId;
    private String requestUri;
    private LocalDateTime requestTimestamp;
    private String ipAddress;
    private String ipValidationResponseCode;
    private String countryCode;
    private String isp;
    private LocalDateTime responseTimestamp;

}
