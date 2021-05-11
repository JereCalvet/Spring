package com.jeremias.oauthtest.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;
import java.util.*;
import static com.fasterxml.jackson.annotation.JsonInclude.*;

@JsonInclude(Include.NON_NULL)
public class ApiExceptionMessage {
    private final int statusCode;
    private final HttpStatus httpStatus;
    private final LocalDateTime timestamp;
    private final String message;
    private final String path;
    private List<ValidationError> errors;
    private Throwable ex;

    private static class ValidationError {
        private final String field;
        private final String message;

        public ValidationError(String field, String message) {
            this.field = field;
            this.message = message;
        }

        public String getField() {
            return field;
        }

        public String getMessage() {
            return message;
        }
    }

    public ApiExceptionMessage(HttpStatus httpStatus, int statusCode, String message, String path) {
        this.httpStatus = httpStatus;
        this.statusCode = statusCode;
        this.message = message;
        this.path = path;
        timestamp = LocalDateTime.now();
    }

    public ApiExceptionMessage(HttpStatus httpStatus, int statusCode, String message, String path, Throwable ex) {
        this.httpStatus = httpStatus;
        this.statusCode = statusCode;
        this.message = message;
        this.path = path;
        this.ex = ex;
        timestamp = LocalDateTime.now();
    }

    public void addValidationError(String field, String message) {
        if (Objects.isNull(errors)) {
            errors = new ArrayList<>();
        }
        errors.add(new ValidationError(field, message));
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public Throwable getEx() {
        return ex;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public List<ValidationError> getErrors() {
        return errors;
    }

    public String getPath() {
        return path;
    }
}
