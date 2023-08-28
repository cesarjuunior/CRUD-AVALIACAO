package com.cliente.crudcliente.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteDTO {

    private Long id;
    private String nome;
    private String tipo;
    private String cpfCnpj;
    private String rgIe;
    private Date dataCadastro;
    private boolean status;
    private List<TelefoneContatoDTO> contato;
}
