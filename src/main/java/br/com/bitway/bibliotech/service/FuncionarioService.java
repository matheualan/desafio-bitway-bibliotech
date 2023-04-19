package br.com.bitway.bibliotech.service;

import br.com.bitway.bibliotech.dto.FuncionarioDTO;
import br.com.bitway.bibliotech.model.Funcionario;
import br.com.bitway.bibliotech.repository.FuncionarioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;

    public FuncionarioService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public FuncionarioDTO salvarDTO(FuncionarioDTO funcionarioDTO) {
        var funcionario = new Funcionario();
        BeanUtils.copyProperties(funcionarioDTO, funcionario);
        funcionarioRepository.save(funcionario);
        return funcionarioDTO;
    }

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

    public Optional<FuncionarioDTO> attFuncionarioPorRegistro(String cpf, FuncionarioDTO funcionarioDTO) {
        Funcionario funcionarioEntidade = funcionarioRepository.findByRegistro(cpf).get();
        funcionarioEntidade.setNome(funcionarioDTO.getNome());
        funcionarioEntidade.setCargo(funcionarioDTO.getCargo());
        funcionarioEntidade.setSalario(funcionarioDTO.getSalario());
        funcionarioRepository.save(funcionarioEntidade);
        var funcionarioOTD = new FuncionarioDTO(funcionarioEntidade);
        return Optional.of(funcionarioOTD);
    }

    public void deletarPorRegistro(String registro) {
        Funcionario funcionario = verifyIfExits(registro);
        funcionarioRepository.delete(funcionario);
    }

    public Funcionario verifyIfExits(String registro) {
        return funcionarioRepository.findByRegistro(registro)
                .orElseThrow(() -> new NoSuchElementException("Funcionário não encontrado."));
    }

}
