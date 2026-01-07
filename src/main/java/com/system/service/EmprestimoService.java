package com.system.service;

import com.system.dao.EmprestimoDAO;
import com.system.dao.LivroDAO;
import com.system.model.Emprestimo;
import com.system.model.Livro;

import java.time.LocalDate;
import java.util.List;

public class EmprestimoService {

    private final EmprestimoDAO emprestimoDAO;
    private final LivroDAO livroDAO;

    public EmprestimoService(){
        this.emprestimoDAO = new EmprestimoDAO();
        this.livroDAO = new LivroDAO();
    }

    public void realizarEmprestimo(Emprestimo emprestimo){
        Livro livro = livroDAO.findById(emprestimo.getIdLivro());

        if(livro == null){
            throw new RuntimeException("!! Livro não encontrado !!");
        }

        if(!livro.isDisponivel()){
            throw new RuntimeException("!! Livro não está disponivel !!");
        }

        emprestimoDAO.create(emprestimo);
    }

    public void devolverEmprestimo(int idEmprestimo){
        Emprestimo emprestimo = emprestimoDAO.findById(idEmprestimo);
        if(emprestimo == null){
            throw new RuntimeException("!! Emprestimo não foi encontrado !!");
        }
        int idLivro = emprestimo.getIdLivro();
        emprestimoDAO.devolver(idEmprestimo, idLivro, LocalDate.now());
    }

    public void atualizar(int idEmprestimo, Emprestimo emprestimo){
        emprestimoDAO.update(idEmprestimo, emprestimo);
    }

    public List<Emprestimo> listar(){
        return emprestimoDAO.read();
    }

    public Emprestimo buscarId(int idEmprestimo){
        return emprestimoDAO.findById(idEmprestimo);
    }

    public void remover(int idEmprestimo){
        emprestimoDAO.delete(idEmprestimo);
    }
}
