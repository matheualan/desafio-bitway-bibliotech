package br.com.bitway.bibliotech.exceptions;

import java.util.NoSuchElementException;

public class ClienteNaoEncontradoException extends NoSuchElementException {

    public ClienteNaoEncontradoException(String cpf) {
        super(String.format("Cliente portador do CPF %s não encontrado.", cpf));
    }

}
