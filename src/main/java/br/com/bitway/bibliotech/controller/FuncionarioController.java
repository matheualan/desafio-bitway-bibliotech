package br.com.bitway.bibliotech.controller;

import br.com.bitway.bibliotech.dto.FuncionarioDTO;
import br.com.bitway.bibliotech.service.FuncionarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping(value = "/funcionario")
public class FuncionarioController {

    private final FuncionarioService funcionarioService;

    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @PostMapping(value = "/salvar")
    public ResponseEntity<FuncionarioDTO> salvar(@RequestBody @Valid FuncionarioDTO funcionarioDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(funcionarioService.salvarDTO(funcionarioDTO));
    }

    @DeleteMapping(value = "/deletar/{registro}")
    public ResponseEntity<Optional<FuncionarioDTO>> deletarPorRegistro(@PathVariable(value = "registro") String registro) {
        funcionarioService.deletarPorRegistro(registro);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
