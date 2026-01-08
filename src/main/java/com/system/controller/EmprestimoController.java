package com.system.controller;

import com.system.model.Emprestimo;
import com.system.service.EmprestimoService;

import java.util.List;

public class EmprestimoController {

    private final EmprestimoService emprestimoService;

    public EmprestimoController(){
        this.emprestimoService = new EmprestimoService();
    }

    public void realizarEmprestimo(Emprestimo emprestimo){
        emprestimoService.realizar(emprestimo);
    }

    public void devolverEmprestimo(int idEmprestimo){
        emprestimoService.devolver(idEmprestimo);
    }

    public void atualizarEmprestimo(int idEmprestimo, Emprestimo emprestimo){
        emprestimoService.atualizar(idEmprestimo, emprestimo);
    }

    public List<Emprestimo> listarEmprestimos(){
        return emprestimoService.listar();
    }

    public Emprestimo buscarEmprestimo(int idEmprestimo){
        return emprestimoService.buscarId(idEmprestimo);
    }

    public void removerEmprestimo(int idEmprestimo){
        emprestimoService.remover(idEmprestimo);
    }
}
