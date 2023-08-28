package com.cliente.crudcliente.repository;

import com.cliente.crudcliente.model.entity.TelefoneContato;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TelefoneContatoRepository extends CrudRepository<TelefoneContato, Long> {

    void deleteAllByIdCliente(Long idCliente);
}
