package com.cliente.crudcliente.controllers;

import com.cliente.crudcliente.model.StatusEnum;
import com.cliente.crudcliente.model.dto.ClienteDTO;
import com.cliente.crudcliente.model.dto.ClienteRequestDTO;
import com.cliente.crudcliente.service.ClienteService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/cliente")
public class ClienteController {

    private final ClienteService clienteService;

    ClienteController(ClienteService clienteService){
        this.clienteService = clienteService;
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> salvarCliente(@RequestBody ClienteRequestDTO requestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.clienteService.salvarCliente(requestDTO));
    }

    @GetMapping
    public ResponseEntity<Page<ClienteDTO>> buscarClientes(
            @RequestParam(value = "nome", required = false) String nome,
            @RequestParam(value = "status", required = false) StatusEnum status,
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size
    ){
        return ResponseEntity.ok().body(this.clienteService.buscarClientes(nome, status, PageRequest.of(page, size)));
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<ClienteDTO> buscarClientesPorId(@PathVariable Long codigo){
        return ResponseEntity.ok().body(this.clienteService.buscarClientePorID(codigo));
    }


    @DeleteMapping(value = "/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarCliente(@PathVariable("codigo") Long codigo){
        this.clienteService.deletarCliente(codigo);
    }

    @PutMapping
    public ResponseEntity<ClienteDTO> atualizarCliente(@RequestBody ClienteDTO clienteDTO){
        return ResponseEntity.ok().body(this.clienteService.atualizarCliente(clienteDTO));
    }
}
