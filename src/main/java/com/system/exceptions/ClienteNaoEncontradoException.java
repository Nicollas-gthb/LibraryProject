package com.system.exceptions;

public class ClienteNaoEncontradoException extends NotFoundException{

    public ClienteNaoEncontradoException(int id){
        super("Cliente com ID: " + id + " nao encontrado !!");
    }
}
