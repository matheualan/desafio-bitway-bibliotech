package br.com.bitway.bibliotech.service;

import br.com.bitway.bibliotech.dto.ClienteDTO;
import br.com.bitway.bibliotech.dto.EnderecoDTO;
import br.com.bitway.bibliotech.exceptions.ClienteNotFoundException;
import br.com.bitway.bibliotech.model.Cliente;
import br.com.bitway.bibliotech.model.Endereco;
import br.com.bitway.bibliotech.repository.ClienteRepository;
import br.com.bitway.bibliotech.repository.EnderecoRepository;
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
    private final EnderecoRepository enderecoRepository;

    public ClienteService(ClienteRepository clienteRepository,
                          EnderecoRepository enderecoRepository) {
        this.clienteRepository = clienteRepository;
        this.enderecoRepository = enderecoRepository;
    }

    public ClienteDTO salvarDTO(ClienteDTO clienteDTO) {
        var cliente = new Cliente();
        BeanUtils.copyProperties(clienteDTO, cliente);
        clienteRepository.save(cliente);
        return clienteDTO;
    }

    public ClienteDTO salvarDTOClienteEEndereco(ClienteDTO clienteDTO) {
        var clienteEntidade = new Cliente();

        clienteEntidade.setNome(clienteDTO.getNome());
        clienteEntidade.setCpf(clienteDTO.getCpf());

        for (EnderecoDTO enderecoDTO : clienteDTO.getEnderecos()) {
            var enderecoEntidade = new Endereco();
            BeanUtils.copyProperties(enderecoDTO, enderecoEntidade);
            clienteEntidade.getEnderecos().add(enderecoEntidade);
        }

        clienteRepository.save(clienteEntidade);
//        criar uma variável DTO para receber os dados do clienteDTO do parâmetro

        return new ClienteDTO(clienteEntidade);
    }

    public ClienteDTO salvarClienteAndEndereco(Cliente cliente) {
        var clienteDTO = new ClienteDTO();

        clienteDTO.setNome(cliente.getNome());
        clienteDTO.setCpf(cliente.getCpf());
        clienteDTO.setEnderecos(new ArrayList<>());

//        List<EnderecoDTO> enderecoDTOList = new ArrayList<>();
        for (Endereco e : cliente.getEnderecos()) {
            var enderecoDTO = new EnderecoDTO();
//            enderecoDTO.setCep(e.getCep());
//            enderecoDTO.setRua(e.getRua());
//            enderecoDTO.setNumero(e.getNumero());
//            enderecoDTO.setBairro(e.getBairro());
//            enderecoDTO.setCidade(e.getCidade());
//            enderecoDTO.setComplemento(e.getComplemento());
            BeanUtils.copyProperties(e, enderecoDTO);
//            enderecoDTOList.add(enderecoDTO);
//            Testar substituir a linha de cima por a linha de baixo
            clienteDTO.getEnderecos().add(enderecoDTO);
        }

//        clienteDTO.setEnderecos(enderecoDTOList);

        clienteRepository.save(cliente);

        return clienteDTO;
    }

    public List<ClienteDTO> listarDTO() {
        List<Cliente> clientes = clienteRepository.findAll();
        List<ClienteDTO> clienteDTOS = new ArrayList<>();
        for (Cliente cliente : clientes) {
            var clienteDTO = new ClienteDTO();
            BeanUtils.copyProperties(cliente, clienteDTO);
            clienteDTOS.add(clienteDTO);
        }
        return clienteDTOS;
    }

    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    public Page<ClienteDTO> findAllPage(Pageable pageable) {
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

    public void deletarPorId(Integer id) {
        clienteRepository.deleteById(id);
    }

    public void deletePorCpf(String cpf) {
        Cliente clienteEntidade = verifyIfExists(cpf);
        clienteRepository.delete(clienteEntidade);
    }

    public Cliente verifyIfExists(String cpf) {
        return clienteRepository.findByCpf(cpf).orElseThrow(() -> new ClienteNotFoundException(cpf));
    }

}
