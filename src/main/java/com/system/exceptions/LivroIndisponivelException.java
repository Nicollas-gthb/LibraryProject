package com.system.exceptions;

public class LivroIndisponivelException extends BusinessException{

    public LivroIndisponivelException(int id) {
        super("Livro com ID: " + id + " nao esta disponivel !!");
    }
}
