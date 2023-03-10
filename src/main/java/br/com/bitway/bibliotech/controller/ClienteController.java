package br.com.bitway.bibliotech.controller;

import br.com.bitway.bibliotech.dto.ClienteDTO;
import br.com.bitway.bibliotech.model.Cliente;
import br.com.bitway.bibliotech.service.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/cliente")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping(value = "/salvar")
    public ResponseEntity<ClienteDTO> salvarDTO(@RequestBody @Valid ClienteDTO clienteDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.salvarDTO(clienteDTO));
    }

    @GetMapping(value = "/listar")
    public ResponseEntity<List<ClienteDTO>> listarDTO() {
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.listarDTO());
    }

    @GetMapping(value = "/buscarPorCPF/{cpf}")
    public ResponseEntity<Optional<ClienteDTO>> buscarPorCpf(@PathVariable(value = "cpf") String cpf) {
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.findByCpf(cpf));
    }

//    @PatchMapping(value = "/atualizar/{cpf}")
//    public ResponseEntity<Optional<ClienteDTO>> atualizar(@RequestBody @Valid ClienteDTO clienteDTO,
//                                                @PathVariable(value = "cpf") String cpf) {
//        Optional<ClienteDTO> clienteOptDTO = clienteService.findByCpf(cpf);
//        return ResponseEntity.status(HttpStatus.OK).body(clienteOptDTO);
//    }

}
