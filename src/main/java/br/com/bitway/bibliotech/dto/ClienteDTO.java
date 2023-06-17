package br.com.bitway.bibliotech.dto;

import br.com.bitway.bibliotech.model.Cliente;
import br.com.bitway.bibliotech.model.Endereco;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class ClienteDTO {

    @NotBlank(message = "Campo obrigatório. Não pode ser nulo nem vazio.")
    @Size(min = 2, max = 70)
    private String nome;

    @NotBlank(message = "Campo obrigatório")
//    @Pattern(regexp = "^\\d{11}$")
    @Size(min = 11, max = 11, message = "Campo obrigatório. Deve conter 11 dígitos numéricos.")
    private String cpf;

    private List<EnderecoDTO> enderecos;

    public ClienteDTO() {
    }

    public ClienteDTO(String nome, String cpf, List<EnderecoDTO> enderecos) {
        this.nome = nome;
        this.cpf = cpf;
        this.enderecos = enderecos;
    }

    public ClienteDTO(Cliente cliente) {
        this.nome = cliente.getNome();
        this.cpf = cliente.getCpf();
        this.enderecos = new ArrayList<>();
        for (Endereco enderecoEntidade : cliente.getEnderecos()) {
            enderecos.add(new EnderecoDTO(enderecoEntidade));
        }
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

    public List<EnderecoDTO> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<EnderecoDTO> enderecos) {
        this.enderecos = enderecos;
    }
}
