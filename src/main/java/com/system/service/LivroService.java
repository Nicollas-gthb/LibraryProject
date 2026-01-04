package com.system.service;

import com.system.dao.LivroDAO;
import com.system.model.Livro;

import java.util.List;

public class LivroService {

    private final LivroDAO livroDAO;

    public LivroService() {
        this.livroDAO = new LivroDAO();
    }

    public void cadastrar(Livro livro){
        livroDAO.create(livro);
    }

    public void atualizar(Livro livro){
        livroDAO.update(livro);
    }

    public List<Livro> listar(){
        return livroDAO.read();
    }

    public Livro buscarId(int idLivro){
        return livroDAO.findById(idLivro);
    }

    public void remover(int idLivro){
        livroDAO.delete(idLivro);
    }
}
