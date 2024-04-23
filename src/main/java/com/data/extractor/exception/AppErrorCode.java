package com.data.extractor.exception;

public enum AppErrorCode {

    INVALID_FILE_FORMAT("Invalid file format"),
    INVALID_FILE_TYPE("Uploaded file type is not supported"),
    INVALID_FILE_CONTENT("Uploaded file is empty"),
    INVALID_REQUESTER("Not a valid IP address/requester");

    private final String message;

    AppErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
