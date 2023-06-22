package br.com.bitway.bibliotech.service;

import br.com.bitway.bibliotech.dto.ClienteDTO;
import br.com.bitway.bibliotech.dto.EnderecoDTO;
import br.com.bitway.bibliotech.exceptions.ClienteNaoEncontradoException;
import br.com.bitway.bibliotech.model.Cliente;
import br.com.bitway.bibliotech.model.Endereco;
import br.com.bitway.bibliotech.repository.ClienteRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public ClienteDTO salvarDTO(ClienteDTO clienteDTO) {
        var cliente = new Cliente(clienteDTO);
        for (Endereco endereco : cliente.getEnderecos()) {
            endereco.setCliente(cliente);
        }
        clienteRepository.save(cliente);
        return clienteDTO;
    }

    public ClienteDTO salvarEntidade(Cliente cliente) {
        var clienteDTO = new ClienteDTO(cliente);
        for (Endereco endereco : cliente.getEnderecos()) {
            endereco.setCliente(cliente);
        }
        clienteRepository.save(cliente);
        return clienteDTO;
    }

    public List<ClienteDTO> listarDTO() {
        List<Cliente> clientes = clienteRepository.findAll();
        List<ClienteDTO> clienteDTOS = new ArrayList<>();
        for (Cliente cliente : clientes) {
            var clienteDTO = new ClienteDTO(cliente);
            clienteDTOS.add(clienteDTO);
        }
        return clienteDTOS;
    }

    public List<Cliente> listarEntidade() {
        return clienteRepository.findAll();
    }

    public Page<ClienteDTO> listaPaginada(Pageable pageable) {
        List<Cliente> clientePage = clienteRepository.findAll(pageable).toList();
        List<ClienteDTO> listDTO = new ArrayList<>();

        for (Cliente cliente : clientePage) {
            var clienteDTO = new ClienteDTO();
            clienteDTO.setNome(cliente.getNome());
            clienteDTO.setCpf(cliente.getCpf());

            List<EnderecoDTO> enderecoDTOList = new ArrayList<>();
            for (Endereco endereco : cliente.getEnderecos()) {
                var enderecoDTO = new EnderecoDTO();
                BeanUtils.copyProperties(endereco, enderecoDTO);
                enderecoDTOList.add(enderecoDTO);
            }

            clienteDTO.setEnderecos(enderecoDTOList);
            listDTO.add(clienteDTO);
        }
        Page<ClienteDTO> clienteDTOPage = new PageImpl<ClienteDTO>(listDTO);
        return clienteDTOPage;
    }

    public Optional<ClienteDTO> findByCpf(String cpf) {
        Cliente clienteEntidade = verifyIfExists(cpf);
        ClienteDTO clienteDTO = new ClienteDTO(clienteEntidade);
        return Optional.of(clienteDTO);
    }

    public Optional<ClienteDTO> atualizarClientePorCpf(String cpf, ClienteDTO clienteDTO) {
        Cliente cliente = verifyIfExists(cpf);
        BeanUtils.copyProperties(clienteDTO, cliente);
        clienteRepository.save(cliente);
        var clientDTO = new ClienteDTO(cliente);
        return Optional.of(clientDTO);
    }

    public void deletarPorId(Integer id) {
        clienteRepository.deleteById(id);
    }

    public void deletarPorCpf(String cpf) {
        Cliente clienteEntidade = verifyIfExists(cpf);
        clienteRepository.delete(clienteEntidade);
    }

    public Cliente verifyIfExists(String cpf) {
        return clienteRepository.findByCpf(cpf).orElseThrow(() -> new ClienteNaoEncontradoException(cpf));
    }

}
