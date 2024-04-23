package com.data.extractor.exception;

public class InvalidFileFormatException extends RuntimeException {

    private final AppErrorCode errorCode;

    public InvalidFileFormatException(AppErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public AppErrorCode getErrorCode() {
        return errorCode;
    }

}
