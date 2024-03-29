package br.com.bitway.bibliotech.controller;

import br.com.bitway.bibliotech.dto.ClienteDTO;
import br.com.bitway.bibliotech.exceptions.ClienteNaoEncontradoException;
import br.com.bitway.bibliotech.model.Cliente;
import br.com.bitway.bibliotech.service.ClienteService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

    @ApiResponse(responseCode = "201 - Created", description = "Deve retornar 201 - Created ao salvar no banco de dados")
    @PostMapping(value = "/salvarDTO")
    public ResponseEntity<ClienteDTO> salvarDTO(@RequestBody @Valid ClienteDTO clienteDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.salvarDTO(clienteDTO));
    }

    @PostMapping(value = "/salvarEntidade")
    public ResponseEntity<ClienteDTO> salvarClienteAndEndereco(@RequestBody @Valid Cliente cliente) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.salvarEntidade(cliente));
    }

    @GetMapping(value = "/listarDTO")
    public ResponseEntity<List<ClienteDTO>> listarDTO() {
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.listarDTO());
    }

    @GetMapping(value = "/listarEntidade")
    public ResponseEntity<List<Cliente>> listClient() {
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.listarEntidade());
    }

    @GetMapping(value = "/paginacao")
    public ResponseEntity<Page<ClienteDTO>> listaPaginada(@PageableDefault(page = 0, size = 2) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.listaPaginada(pageable));
    }

    @GetMapping(value = "/buscarPorCPF/{cpf}")
    public ResponseEntity<Optional<ClienteDTO>> buscarPorCpfDTO(@PathVariable(value = "cpf") String cpf) {
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.findByCpf(cpf));
    }

    @PatchMapping(value = "/atualizarPorCpf/{cpf}")
    public ResponseEntity<Optional<ClienteDTO>> atualizarPorCpfDTO(@PathVariable(value = "cpf") String cpf,
                                                                   @RequestBody @Valid ClienteDTO clienteDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.atualizarClientePorCpf(cpf, clienteDTO));
    }

    @DeleteMapping(value = "/deletarPorId/{id}")
    public ResponseEntity<Optional<ClienteDTO>> deletarPorId(@PathVariable(value = "id") Integer id) {
        clienteService.deletarPorId(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping(value = "/deletarPorCpf/{cpf}")
    public ResponseEntity<Optional<ClienteDTO>> deletarPorCpf(@PathVariable(value = "cpf") String cpf) {
        try {
            clienteService.deletarPorCpf(cpf);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (ClienteNaoEncontradoException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
