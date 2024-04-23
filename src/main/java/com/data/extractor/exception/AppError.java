package com.data.extractor.exception;

import java.time.Instant;

public record AppError(Instant timestamp, AppErrorCode errorCode, String message) {
}
