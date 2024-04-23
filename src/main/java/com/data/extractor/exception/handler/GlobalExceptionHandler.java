package com.data.extractor.exception.handler;

import com.data.extractor.exception.AppError;
import com.data.extractor.exception.InvalidFileFormatException;
import com.data.extractor.exception.UnAuthorizedRequesterException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidFileFormatException.class)
    public ResponseEntity<AppError> invalidFileExceptionHandler(InvalidFileFormatException ex) {
        AppError errorResponse = new AppError(Instant.now(), ex.getErrorCode(), ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnAuthorizedRequesterException.class)
    public ResponseEntity<AppError> invalidFileExceptionHandler(UnAuthorizedRequesterException ex) {
        AppError errorResponse = new AppError(Instant.now(), ex.getErrorCode(), ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
    }

}
