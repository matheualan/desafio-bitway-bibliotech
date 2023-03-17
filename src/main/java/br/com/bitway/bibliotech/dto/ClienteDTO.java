package br.com.bitway.bibliotech.dto;

import br.com.bitway.bibliotech.model.Cliente;

import javax.validation.constraints.*;

public class ClienteDTO {

    @NotBlank(message = "Campo obrigatório. Não pode ser nulo nem vazio.")
    @Size(min = 2, max = 70)
    private String nome;

    @NotBlank(message = "Campo obrigatório")
    @Pattern(regexp = "^\\d{11}$")
//    @Size(min = 11, max = 11, message = "Campo obrigatório devendo conter 11 dígitos numéricos.")
    private String cpf;

    public ClienteDTO() {
    }

    public ClienteDTO(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }

    public ClienteDTO(Cliente cliente) {
        nome = cliente.getNome();
        cpf = cliente.getCpf();
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

}
