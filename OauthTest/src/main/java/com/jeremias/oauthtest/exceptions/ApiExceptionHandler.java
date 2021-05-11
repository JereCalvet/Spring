package com.jeremias.oauthtest.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestControllerAdvice
public class ApiExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiExceptionHandler.class);
    private static final String ARGS_NOT_VALID_EXCEPTION_MSG = "Validation error. Check 'errors' field for details.";
    private static final String INTERNAL_SERVER_EXCEPTION_MSG = "Unknown error occurred";

    @ExceptionHandler(EmailAlreadyTakenException.class)
    public ResponseEntity<ApiExceptionMessage> handleEmailAlreadyTakenException(EmailAlreadyTakenException ex, HandlerMethod handlerMethod, HttpServletRequest request) {
        final var responseBody = new ApiExceptionMessage(
                HttpStatus.GONE,
                HttpStatus.GONE.value(),
                ex.getMessage(),
                request.getRequestURI());
        final String loggerMessage = buildLoggerMessage(ex, handlerMethod, request);
        LOGGER.error(loggerMessage);
        return buildResponseEntity(responseBody);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiExceptionMessage> handleUserDtoArgumentsNotValid(MethodArgumentNotValidException ex, HandlerMethod handlerMethod, HttpServletRequest request) {
        final var responseBody = new ApiExceptionMessage(
                HttpStatus.BAD_REQUEST,
                HttpStatus.BAD_REQUEST.value(),
                ARGS_NOT_VALID_EXCEPTION_MSG,
                request.getRequestURI());
        final List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        fieldErrors.forEach(error -> responseBody.addValidationError(error.getField(), error.getDefaultMessage()));
        final String loggerMessage = buildLoggerMessage(ex, handlerMethod, request);
        LOGGER.error(loggerMessage);
        return buildResponseEntity(responseBody);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiExceptionMessage> handleUserNotFoundException(UserNotFoundException ex, HandlerMethod handlerMethod, HttpServletRequest request) {
        final var responseBody = new ApiExceptionMessage(
                HttpStatus.NOT_FOUND,
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                request.getRequestURI());
        final String loggerMessage = buildLoggerMessage(ex, handlerMethod, request);
        LOGGER.error(loggerMessage);
        return buildResponseEntity(responseBody);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiExceptionMessage> handleAllUncaughtException(Exception ex, HandlerMethod handlerMethod, HttpServletRequest request) {
        final var responseBody = new ApiExceptionMessage(
                HttpStatus.INTERNAL_SERVER_ERROR,
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                INTERNAL_SERVER_EXCEPTION_MSG,
                request.getRequestURI());
        final String loggerMessage = buildLoggerMessage(ex, handlerMethod, request);
        LOGGER.error(loggerMessage);
        return buildResponseEntity(responseBody);
    }

    private ResponseEntity<ApiExceptionMessage> buildResponseEntity(ApiExceptionMessage apiExceptionMessage) {
        return new ResponseEntity<>(apiExceptionMessage, apiExceptionMessage.getHttpStatus());
    }

    private String buildLoggerMessage(Throwable ex, HandlerMethod handlerMethod, HttpServletRequest request) {
        final String controllerName = handlerMethod.getMethod().getDeclaringClass().getSimpleName();
        final String method = handlerMethod.getMethod().getName();
        final String path = request.getRequestURI();
        final String simpleName = ex.getClass().getSimpleName();
        return String.format("Handling %s at %s : %s : %s", simpleName, controllerName, method, path);
    }
}
