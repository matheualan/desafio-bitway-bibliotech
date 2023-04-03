package br.com.bitway.bibliotech.service;

import br.com.bitway.bibliotech.dto.ClienteDTO;
import br.com.bitway.bibliotech.dto.EnderecoDTO;
import br.com.bitway.bibliotech.exceptions.ClienteNotFoundException;
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
public class ClienteService implements ClienteServiceRepo {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public ClienteDTO salvarDTO(ClienteDTO clienteDTO) {
        var cliente = new Cliente();
        BeanUtils.copyProperties(clienteDTO, cliente);
        clienteRepository.save(cliente);
        return clienteDTO;
    }

    @Override
    public List<ClienteDTO> listarDTO() {
        List<Cliente> clientes = clienteRepository.findAll();
        List<ClienteDTO> clienteDTOS = new ArrayList<>();
        for(Cliente cliente : clientes) {
            var clienteDTO = new ClienteDTO();
            BeanUtils.copyProperties(cliente, clienteDTO);
            clienteDTOS.add(clienteDTO);
        }
        return clienteDTOS;
    }

    public Page<ClienteDTO> findAllPage(Pageable pageable) {
        List<Cliente> clientePage = clienteRepository.findAll(pageable).toList();
//        List<Cliente> clientePage = clienteRepository.findAll();
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
        }
        Page<ClienteDTO> clienteDTOPage = new PageImpl<ClienteDTO>(listDTO);
        return clienteDTOPage;
    }

//        Retorna uma lista paginada de ENTIDADE (OK)
//    public Page<Cliente> findAllPage(Pageable pageable) {
//        return clienteRepository.findAll(pageable);
//    }

    public Optional<ClienteDTO> findByCpf(String cpf) {
        Cliente clienteEntidade = verifyIfExists(cpf);
        ClienteDTO clienteDTO = new ClienteDTO(clienteEntidade);
        return Optional.of(clienteDTO);
    }

    public Optional<ClienteDTO> atualizarClientePorCpf(String cpf, ClienteDTO clienteDTO) {
        Cliente cliente = verifyIfExists(cpf);
//        cliente.setNome(clienteDTO.getNome());
//        cliente.setCpf(clienteDTO.getCpf());
        BeanUtils.copyProperties(clienteDTO, cliente);
        clienteRepository.save(cliente);
        var clientDTO = new ClienteDTO(cliente);
        return Optional.of(clientDTO);
    }

    public void deletePorCpf(String cpf) {
        Cliente clienteEntidade = verifyIfExists(cpf);
        clienteRepository.delete(clienteEntidade);
    }

    public Cliente verifyIfExists(String cpf) {
        return clienteRepository.findByCpf(cpf).orElseThrow(() -> new ClienteNotFoundException(cpf));
    }

}
