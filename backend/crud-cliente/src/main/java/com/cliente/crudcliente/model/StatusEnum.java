package com.cliente.crudcliente.model;

import lombok.Getter;

@Getter
public enum StatusEnum {
    ATIVO(true),
    INATIVO(false);

    boolean value;
    StatusEnum(boolean value){
        this.value = value;
    }
}
