package br.com.bitway.bibliotech.exceptions;

import java.util.NoSuchElementException;

public class ClienteNotFoundException extends NoSuchElementException {

    public ClienteNotFoundException(String cpf) {
        super(String.format("Cliente portador do CPF %d não encontrado.", cpf));
    }

}
