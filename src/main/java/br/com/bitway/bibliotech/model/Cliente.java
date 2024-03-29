package br.com.bitway.bibliotech.model;

import br.com.bitway.bibliotech.dto.ClienteDTO;
import br.com.bitway.bibliotech.dto.EnderecoDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_clientes")
@JsonIgnoreProperties(value = {"idCliente", "criadoEm"})
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCliente;

    @Column(nullable = false, length = 70)
    private String nome;

    @Column(nullable = false, length = 11, unique = true)
    private String cpf;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private final LocalDateTime criadoEm = LocalDateTime.now();

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Endereco> enderecos = new ArrayList<>();

    public Cliente() {
    }

    public Cliente(ClienteDTO clienteDTO) {
        nome = clienteDTO.getNome();
        cpf = clienteDTO.getCpf();
        enderecos = new ArrayList<>();
        for (EnderecoDTO endDTO : clienteDTO.getEnderecos()) {
            enderecos.add(new Endereco(endDTO));
        }
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }
}
