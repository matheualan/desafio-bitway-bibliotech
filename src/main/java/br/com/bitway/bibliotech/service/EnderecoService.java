package br.com.bitway.bibliotech.service;

import br.com.bitway.bibliotech.dto.EnderecoDTO;
import br.com.bitway.bibliotech.model.Endereco;
import br.com.bitway.bibliotech.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    public EnderecoDTO salvarEndereco(Endereco endereco) {
        var enderecoDTO = new EnderecoDTO(endereco);
        enderecoRepository.save(endereco);
        return enderecoDTO;
    }

    public List<Endereco> listarEnderecos() {
        return null;
    }

    public List<EnderecoDTO> listarEnderecosDTO() {
        return null;
    }

}
