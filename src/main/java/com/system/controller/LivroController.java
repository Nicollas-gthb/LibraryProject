package com.system.controller;

import com.system.model.Livro;
import com.system.service.LivroService;

import java.util.List;

public class LivroController {

    private final LivroService livroService;

    public LivroController(){
        this.livroService = new LivroService();
    }

    public void cadastrarLivro(Livro livro){
        livroService.cadastrar(livro);
    }

    public void atualizarLivro(Livro livro){
        livroService.atualizar(livro);
    }

    public List<Livro> listarLivro(){
        return livroService.listar();
    }

    public Livro buscarLivro(int idLivro){
        return livroService.buscarId(idLivro);
    }

    public void excluirLivro(int idLivro){
        livroService.remover(idLivro);
    }
}
