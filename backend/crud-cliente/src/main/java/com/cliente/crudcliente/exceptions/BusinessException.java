package com.cliente.crudcliente.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@AllArgsConstructor
@Builder
public class BusinessException extends RuntimeException {

    private final Integer status;
    private final String message;
    private final List<String> details;
}
