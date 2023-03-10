package br.com.bitway.bibliotech.dto;

import javax.validation.constraints.*;

public class ClienteDTO {

    @NotBlank
    @Size(min = 2, max = 70)
    private String nome;

    @NotNull(message = "Campo obrigatório")
    private String cpf;

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
