package com.cliente.crudcliente.service;

import com.cliente.crudcliente.model.StatusEnum;
import com.cliente.crudcliente.model.dto.ClienteDTO;
import com.cliente.crudcliente.model.dto.ClienteRequestDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClienteService {

    ClienteDTO salvarCliente(ClienteRequestDTO requestDTO);

    Page<ClienteDTO> buscarClientes(String nome, StatusEnum status, Pageable pageable);

    ClienteDTO buscarClientePorID(Long codigo);

    void deletarCliente(Long codigo);

    ClienteDTO atualizarCliente(ClienteDTO cliente);
}
