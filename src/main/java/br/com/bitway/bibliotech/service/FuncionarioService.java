package br.com.bitway.bibliotech.service;

import br.com.bitway.bibliotech.dto.FuncionarioDTO;
import br.com.bitway.bibliotech.model.Funcionario;
import br.com.bitway.bibliotech.repository.FuncionarioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FuncionarioService implements FuncionarioServiceRepo {

    private final FuncionarioRepository funcionarioRepository;

    public FuncionarioService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    @Override
    public FuncionarioDTO salvarDTO(FuncionarioDTO funcionarioDTO) {
        var funcionario = new Funcionario();
        BeanUtils.copyProperties(funcionarioDTO, funcionario);
        funcionarioRepository.save(funcionario);
        return funcionarioDTO;
    }

    @Override
    public List<FuncionarioDTO> listarDTO() {
        List<Funcionario> funcionarios = funcionarioRepository.findAll();
        List<FuncionarioDTO> funcionarioDTOS = new ArrayList<>();
        for(Funcionario funcionario : funcionarios) {
            var funcionarioDTO = new FuncionarioDTO();
            BeanUtils.copyProperties(funcionario, funcionarioDTO);
            funcionarioDTOS.add(funcionarioDTO);
        }
        return funcionarioDTOS;
    }

}
