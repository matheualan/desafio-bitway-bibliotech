package br.com.bitway.bibliotech.controller;

import br.com.bitway.bibliotech.dto.ClienteDTO;
import br.com.bitway.bibliotech.service.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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

}
