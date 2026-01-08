package com.system.exceptions;

public class EmprestimoNaoEncontradoException extends NotFoundException{

    public EmprestimoNaoEncontradoException(){
        super("Emprestimo nao encontrado!");
    }

    public EmprestimoNaoEncontradoException(int id){
        super("Emprestimo com ID: " + id + " nao encontrado !!");
    }
}
