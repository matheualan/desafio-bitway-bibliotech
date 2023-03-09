package br.com.bitway.bibliotech.service;

import br.com.bitway.bibliotech.dto.ClienteDTO;

import java.util.List;

public interface ClienteServiceRepo {

    ClienteDTO salvarDTO(ClienteDTO clienteDTO);
    List<ClienteDTO> listarDTO();

}
