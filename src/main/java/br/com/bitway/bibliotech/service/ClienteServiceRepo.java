package br.com.bitway.bibliotech.service;

import br.com.bitway.bibliotech.dto.ClienteDTO;
import br.com.bitway.bibliotech.model.Cliente;

import java.util.List;

public interface ClienteServiceRepo {

    ClienteDTO salvarDTO(ClienteDTO clienteDTO);
    List<ClienteDTO> listarDTO();
    ClienteDTO salvarClienteAndEndereco(Cliente cliente);

}
