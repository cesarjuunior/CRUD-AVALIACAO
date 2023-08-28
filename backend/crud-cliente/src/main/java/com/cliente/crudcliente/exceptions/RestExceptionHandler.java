package com.cliente.crudcliente.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public final ResponseEntity<ExceptionResponse> handleBusinessException(BusinessException exception) {
        ExceptionResponse response = ExceptionResponse.builder()
                .code(exception.getMessage())
                .message(exception.getMessage())
                .details(exception.getDetails())
                .build();

        return ResponseEntity.status(exception.getStatus()).body(response);
    }
}
