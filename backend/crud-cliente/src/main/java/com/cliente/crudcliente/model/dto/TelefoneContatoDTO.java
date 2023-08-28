package com.cliente.crudcliente.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TelefoneContatoDTO {

    private Long id;
    private String ddd;
    private String numero;
}
