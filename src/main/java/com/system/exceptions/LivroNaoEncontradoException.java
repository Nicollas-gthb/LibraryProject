package com.system.exceptions;

public class LivroNaoEncontradoException extends NotFoundException{

    public LivroNaoEncontradoException(int id){
        super("Livro com ID: " + id + " nao encontrado !!");
    }
}
