package br.com.bitway.bibliotech.controller;

import br.com.bitway.bibliotech.dto.ClienteDTO;
import br.com.bitway.bibliotech.service.ClienteService;
import br.com.bitway.bibliotech.util.ClienteCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
public class ClienteControllerTest {

    @InjectMocks
    private ClienteController controllerMock;

    @Mock
    private ClienteService serviceMock;

    @BeforeEach
    void setUp() {
        ClienteDTO clienteDTO = new ClienteDTO(ClienteCreator.criarCliente());
        List<ClienteDTO> listDTO = new ArrayList<>();
        listDTO.add(clienteDTO);

        BDDMockito.when(serviceMock.listarDTO()).thenReturn(listDTO);
    }

    @Test
    @DisplayName("Testando método GET")
    void retorna_UmaListaDeClienteDTO_SeObterSucesso() {
        ClienteDTO clienteDTO = ClienteCreator.criarClienteDTO();
        List<ClienteDTO> listResponseEntity = controllerMock.listarDTO().getBody();

        Assertions.assertThat(listResponseEntity).isNotNull().isNotEmpty();
        Assertions.assertThat(listResponseEntity.get(0).getNome()).isEqualTo(clienteDTO.getNome());
//        Assertions.assertThat(listResponseEntity).isNotEmpty();
    }

}
