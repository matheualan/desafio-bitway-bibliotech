package br.com.bitway.bibliotech.service;

import br.com.bitway.bibliotech.dto.ClienteDTO;
import br.com.bitway.bibliotech.model.Cliente;
import br.com.bitway.bibliotech.repository.ClienteRepository;
import org.springframework.beans.BeanUtils;
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

    public Optional<ClienteDTO> findByCpf(String cpf) {
        return clienteRepository.findByCpf(cpf);
    }

}
