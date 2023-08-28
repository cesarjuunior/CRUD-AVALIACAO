package com.cliente.crudcliente.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClienteRequestDTO {

    private String nome;
    private String tipo;
    private String cpfCnpj;
    private String rgIe;
    private List<TelefoneContatoDTO> contato;
}
