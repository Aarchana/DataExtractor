package com.data.extractor.exception;

public class UnAuthorizedRequesterException extends RuntimeException {

    private final  AppErrorCode errorCode;


    public AppErrorCode getErrorCode() {
        return errorCode;
    }

    public UnAuthorizedRequesterException(AppErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

}
