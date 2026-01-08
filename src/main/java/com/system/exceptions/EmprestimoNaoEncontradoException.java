package com.system.exceptions;

public class EmprestimoNaoEncontradoException extends NotFoundException{

    public EmprestimoNaoEncontradoException(int id){
        super("Emprestimo com ID: " + id + " nao encontrado !!");
    }
}
