package br.com.bitway.bibliotech.exceptions;

import lombok.experimental.SuperBuilder;

import java.util.NoSuchElementException;

@SuperBuilder
public class ClienteNotFoundException extends NoSuchElementException {

    public ClienteNotFoundException(String cpf) {
        super(String.format("Cliente portador do CPF %s não encontrado.", cpf));
    }

}
