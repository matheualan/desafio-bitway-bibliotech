package br.com.bitway.bibliotech.dto;

import javax.validation.constraints.*;

public class ClienteDTO {

    @NotBlank
    @Size(min = 2, max = 70)
    private String nome;

    @NotNull(message = "Campo obrigatório")
    @Min(value = 11) //testar se ele está recebendo 11 caracteres
    @Max(value = 11) //ou se está podendo colocar apenas o número 11
    private Long cpf;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getCpf() {
        return cpf;
    }

    public void setCpf(Long cpf) {
        this.cpf = cpf;
    }
}
