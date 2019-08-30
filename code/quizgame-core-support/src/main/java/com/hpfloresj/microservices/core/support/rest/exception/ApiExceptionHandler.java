package com.hpfloresj.microservices.core.support.rest.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that represents the handler of exceptions in the layer RestFull
 */
@RestControllerAdvice
public class ApiExceptionHandler {

    @SuppressWarnings("rawtypes")
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorItem> handle(Exception e) {
        ErrorItem error = new ErrorItem();
        error.setMessage(e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Data
    public static class ErrorItem {
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String code;
        private String message;

    }

    @Data
    public static class ErrorResponse {
        private List<ErrorItem> errors = new ArrayList<>();

        public void addError(ErrorItem error) {
            this.errors.add(error);
        }

    }
}
