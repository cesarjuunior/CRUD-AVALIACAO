package com.cliente.crudcliente.mappers;

import com.cliente.crudcliente.model.dto.ClienteDTO;
import com.cliente.crudcliente.model.dto.ClienteRequestDTO;
import com.cliente.crudcliente.model.dto.TelefoneContatoDTO;
import com.cliente.crudcliente.model.entity.Cliente;
import com.cliente.crudcliente.model.entity.TelefoneContato;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    @Mapping(target = "contato", ignore = true)
    Cliente toEntity(ClienteRequestDTO requestDTO);

    Cliente toEntity(ClienteDTO clienteDTO);

    @Mapping(target = "idCliente", source = "idCliente")
    TelefoneContato toEntity(TelefoneContatoDTO contato, Long idCliente);

    TelefoneContatoDTO toDto(TelefoneContato contato);

    ClienteDTO toDto(Cliente cliente);


}
