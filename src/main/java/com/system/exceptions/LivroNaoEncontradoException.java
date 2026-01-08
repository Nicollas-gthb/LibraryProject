package com.system.exceptions;

public class LivroNaoEncontradoException extends NotFoundException{

    public LivroNaoEncontradoException(){
        super("Livro nao encontrado!");
    }

    public LivroNaoEncontradoException(int id){
        super("Livro com ID: " + id + " nao encontrado !!");
    }
}
