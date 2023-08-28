package com.cliente.crudcliente.service.impl;

import com.cliente.crudcliente.exceptions.BusinessException;
import com.cliente.crudcliente.mappers.ClienteMapper;
import com.cliente.crudcliente.model.StatusEnum;
import com.cliente.crudcliente.model.dto.ClienteDTO;
import com.cliente.crudcliente.model.dto.ClienteRequestDTO;
import com.cliente.crudcliente.model.entity.Cliente;
import com.cliente.crudcliente.model.entity.TelefoneContato;
import com.cliente.crudcliente.repository.ClienteRepository;
import com.cliente.crudcliente.repository.TelefoneContatoRepository;
import com.cliente.crudcliente.service.ClienteService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    private final TelefoneContatoRepository contatoRepository;

    private final ClienteMapper clienteMapper;

    ClienteServiceImpl(ClienteRepository clienteRepository, ClienteMapper clienteMapper,TelefoneContatoRepository contatoRepository){
        this.clienteRepository = clienteRepository;
        this.clienteMapper = clienteMapper;
        this.contatoRepository = contatoRepository;
    }


    @Transactional
    @Override
    public ClienteDTO salvarCliente(ClienteRequestDTO requestDTO) {

        if (this.clienteRepository.findByCpfCnpj(requestDTO.getCpfCnpj()).isPresent()){
            throw new BusinessException(HttpStatus.BAD_REQUEST.value(), "CPF/CNPJ JÁ CADASTRADO", List.of("Já existe um cliente com esse cpf cadastrado em nossa base"));
        }

        Cliente cliente = this.clienteRepository.save(clienteMapper.toEntity(requestDTO));
        List<TelefoneContato> listaContatos = requestDTO.getContato().stream().map(tel -> clienteMapper.toEntity(tel, cliente.getId())).toList();
        this.contatoRepository.saveAll(listaContatos);
        return clienteMapper.toDto(this.clienteRepository.findById(cliente.getId()).orElse(null));
    }

    @Override
    public Page<ClienteDTO> buscarClientes(String nome, StatusEnum status, Pageable pageable) {
        return this.clienteRepository.findAll(createSpecification(nome, status), pageable)
                .map(clienteMapper::toDto);
    }

    @Override
    public ClienteDTO buscarClientePorID(Long codigo) {
        return clienteMapper.toDto(this.clienteRepository.findById(codigo).orElse(null));
    }

    @Override
    public void deletarCliente(Long codigo) {
        this.clienteRepository.deleteById(codigo);
    }

    @Transactional
    @Override
    public ClienteDTO atualizarCliente(ClienteDTO cliente) {
        this.contatoRepository.deleteAllByIdCliente(cliente.getId());
        this.contatoRepository.saveAll(cliente.getContato().stream().map(tel-> clienteMapper.toEntity(tel, cliente.getId())).toList());
        return clienteMapper.toDto(this.clienteRepository.save(clienteMapper.toEntity(cliente)));
    }

    private Specification<Cliente> createSpecification(String nome, StatusEnum status){
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if(nome != null){
                predicates.add(cb.like(cb.upper(root.get("nome")), "%".concat(nome.toUpperCase()).concat("%")));
            }
            if(status != null){
                predicates.add(cb.equal(root.get("status"), status.isValue()));
            }
            return cb.and(predicates.toArray(new Predicate[]{}));
        };
    }
}
