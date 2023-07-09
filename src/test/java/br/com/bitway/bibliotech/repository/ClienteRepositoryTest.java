package br.com.bitway.bibliotech.repository;

import br.com.bitway.bibliotech.model.Cliente;
import br.com.bitway.bibliotech.util.ClienteCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class ClienteRepositoryTest {

    @Autowired
    private ClienteRepository repositoryMock;

    @Test
    @DisplayName("Deve retornar um Cliente após ser salvo no banco de dados.")
    void deveRetornar_UmClienteSalvo_SeObterSucesso() {
        Cliente cliente = ClienteCreator.criarCliente();

        Cliente clienteSalvo = repositoryMock.save(cliente);

        Assertions.assertThat(clienteSalvo).isNotNull();
    }

}
