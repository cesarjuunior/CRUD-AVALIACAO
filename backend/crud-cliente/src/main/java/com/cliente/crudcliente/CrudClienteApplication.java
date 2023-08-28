package com.cliente.crudcliente;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.cliente.crudcliente.repository")
@EntityScan("com.cliente.crudcliente.model.entity")
public class CrudClienteApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrudClienteApplication.class, args);
    }

}
