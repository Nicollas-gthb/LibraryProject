package com.system.exceptions;

public class NotFoundException extends BusinessException{

    public NotFoundException(String mensagem){
        super(mensagem);
    }
}
