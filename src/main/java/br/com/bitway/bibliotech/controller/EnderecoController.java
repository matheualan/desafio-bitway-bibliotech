package br.com.bitway.bibliotech.controller;

import br.com.bitway.bibliotech.dto.EnderecoDTO;
import br.com.bitway.bibliotech.model.Endereco;
import br.com.bitway.bibliotech.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/endereco")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @PostMapping(value = "/salvar")
    public ResponseEntity<EnderecoDTO> salvarEnderecoDTO(@RequestBody Endereco endereco) {
        return ResponseEntity.status(HttpStatus.CREATED).body(enderecoService.salvarEndereco(endereco));
    }

    @GetMapping(value = "/listar")
    public ResponseEntity<List<Endereco>> listarEnderecos() {
        return ResponseEntity.status(HttpStatus.OK).body(enderecoService.listarEnderecos());
    }

    @GetMapping(value = "/listarDTO")
    public ResponseEntity<List<EnderecoDTO>> listarEnderecosDTO() {
        return ResponseEntity.status(HttpStatus.OK).body(enderecoService.listarEnderecosDTO());
    }

}
