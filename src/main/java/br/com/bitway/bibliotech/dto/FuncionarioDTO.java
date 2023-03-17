package br.com.bitway.bibliotech.dto;

import br.com.bitway.bibliotech.model.Funcionario;

import javax.validation.constraints.*;
import java.math.BigDecimal;

public class FuncionarioDTO {

    @NotBlank(message = "Insira seu nome.")
    @Size(min = 3, max = 70)
    private String nome;

    @NotBlank(message = "Campo registro deve conter 7 dígitos numéricos.")
    @Pattern(regexp = "^\\d{7}$")
//    @Size(min, max)
    private String registro;

    @NotBlank
    @Size(min = 3, max = 50)
    private String cargo;

    @NotNull(message = "Campo obrigatório")
    @DecimalMin(value = "0.00")
    @DecimalMax(value = "12000.00")
    private BigDecimal salario;

    public FuncionarioDTO() {
    }

    public FuncionarioDTO(String nome, String registro, String cargo, BigDecimal salario) {
        this.nome = nome;
        this.registro = registro;
        this.cargo = cargo;
        this.salario = salario;
    }

    public FuncionarioDTO(Funcionario funcionario) {
        this.nome = funcionario.getNome();
        this.registro = funcionario.getRegistro();
        this.cargo = funcionario.getCargo();
        this.salario = funcionario.getSalario();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRegistro() {
        return registro;
    }

    public void setRegistro(String registro) {
        this.registro = registro;
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
