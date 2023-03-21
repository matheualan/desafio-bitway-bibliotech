package br.com.bitway.bibliotech.dto;

import javax.validation.constraints.*;

public class EnderecoDTO {

    @NotBlank
    @Size(min = 8, max = 8)
    private String cep;

    @NotBlank(message = "O campo rua deve ser preenchido.")
    @Size(min = 3, max = 255)
    private String rua;

    @NotNull(message = "Campo obrigatório")
    @Digits(integer = 5, fraction = 0) //integer é o tamanho máximo e fraction é se possue casa decimal
    private Integer numero;

    @NotBlank
    @Size(min = 2, max = 50)
    private String bairro;

    @NotBlank
    @Size(min = 2, max = 50)
    private String cidade;

    @Size(min = 3, max = 100)
    private String complemento;

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }
}
