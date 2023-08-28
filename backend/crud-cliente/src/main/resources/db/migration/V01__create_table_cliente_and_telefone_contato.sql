CREATE TABLE CLIENTE(
                                id SERIAL PRIMARY KEY,
                                nome CHARACTER VARYING(100),
                                tipo CHARACTER VARYING(2),
                                cpf_cnpj CHARACTER VARYING(14),
                                rg_ie CHARACTER VARYING(20),
                                data_cadastro DATE,
                                status boolean
);

CREATE TABLE TELEFONE_CONTATO(
                                         id SERIAL PRIMARY KEY,
                                         ddd CHARACTER VARYING(2),
                                         numero CHARACTER VARYING(15),
                                         id_cliente INTEGER,
                                         FOREIGN KEY (id_cliente) REFERENCES CLIENTE (id)
)
