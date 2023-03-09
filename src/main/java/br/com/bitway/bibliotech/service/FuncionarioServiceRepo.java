package br.com.bitway.bibliotech.service;

import br.com.bitway.bibliotech.dto.FuncionarioDTO;

import java.util.List;

public interface FuncionarioServiceRepo {

    FuncionarioDTO salvarDTO(FuncionarioDTO funcionarioDTO);
    List<FuncionarioDTO> listarDTO();

}
