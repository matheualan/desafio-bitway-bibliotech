package br.com.bitway.bibliotech.util;

import br.com.bitway.bibliotech.dto.ClienteDTO;
import br.com.bitway.bibliotech.model.Cliente;

public class ClienteCreator {

    public static Cliente criarCliente() {
        Cliente cliente = new Cliente();
        cliente.setIdCliente(1);
        cliente.setNome("Matheus");
        cliente.setCpf("12312312312");

        return cliente;
    }

    public static ClienteDTO criarClienteDTO() {
        return new ClienteDTO(criarCliente());
    }

}
