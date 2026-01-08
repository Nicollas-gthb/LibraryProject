package com.system.service;

import com.system.dao.ClienteDAO;
import com.system.dao.EmprestimoDAO;
import com.system.dao.LivroDAO;
import com.system.exceptions.ClienteNaoEncontradoException;
import com.system.exceptions.EmprestimoNaoEncontradoException;
import com.system.exceptions.LivroIndisponivelException;
import com.system.exceptions.LivroNaoEncontradoException;
import com.system.model.Cliente;
import com.system.model.Emprestimo;
import com.system.model.Livro;

import java.time.LocalDate;
import java.util.List;

public class EmprestimoService {

    private final EmprestimoDAO emprestimoDAO;
    private final LivroDAO livroDAO;
    private final ClienteDAO clienteDAO;

    public EmprestimoService(){
        this.emprestimoDAO = new EmprestimoDAO();
        this.livroDAO = new LivroDAO();
        this.clienteDAO = new ClienteDAO();
    }

    public void realizar(Emprestimo emprestimo){
        Livro livro = livroDAO.findById(emprestimo.getIdLivro());

        if(livro == null){
            throw new LivroNaoEncontradoException(emprestimo.getIdLivro());
        }

        if(!livro.isDisponivel()){
            throw new LivroIndisponivelException(emprestimo.getIdLivro());
        }

        Cliente cliente = clienteDAO.findById(emprestimo.getIdCliente());

        if(cliente == null){
            throw new ClienteNaoEncontradoException(emprestimo.getIdCliente());
        }

        emprestimoDAO.create(emprestimo);
    }

    public void devolver(int idEmprestimo){
        Emprestimo emprestimo = emprestimoDAO.findById(idEmprestimo);
        if(emprestimo == null){
            throw new EmprestimoNaoEncontradoException(idEmprestimo);
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
        Emprestimo emprestimo = emprestimoDAO.findById(idEmprestimo);

        if(emprestimo == null){
            throw new EmprestimoNaoEncontradoException(idEmprestimo);
        }

        return emprestimo;
    }

    public void remover(int idEmprestimo){
        emprestimoDAO.delete(idEmprestimo);
    }
}
