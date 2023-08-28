package com.cliente.crudcliente.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "CLIENTE")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "tipo")
    private String tipo;

    @Column(name = "cpf_cnpj")
    private String cpfCnpj;

    @Column(name = "rg_ie")
    private String rgIe;

    @CreationTimestamp
    @Column(name = "data_cadastro")
    private LocalDateTime dataCadastro;

    @Column(name = "status")
    private boolean status = true;

    @OneToMany(cascade = {CascadeType.REMOVE}, fetch = FetchType.EAGER, mappedBy = "idCliente")
    private List<TelefoneContato> contato;
}

