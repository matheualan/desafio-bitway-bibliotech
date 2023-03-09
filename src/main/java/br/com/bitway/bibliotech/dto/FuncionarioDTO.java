package br.com.bitway.bibliotech.dto;

import javax.validation.constraints.*;
import java.math.BigDecimal;

public class FuncionarioDTO {

    @NotBlank
    @Size(min = 2, max = 70)
    private String nome;

    @NotBlank
    @Size(min = 3, max = 50)
    private String cargo;

    @NotNull(message = "Campo obrigatório")
    @DecimalMin(value = "0.00")
    @DecimalMax(value = "12000.00")
    private BigDecimal salario;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }
}
