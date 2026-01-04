package com.system.service;

import com.system.dao.ClienteDAO;
import com.system.model.Cliente;

import java.util.List;

public class ClienteService {

    private final ClienteDAO clienteDAO;

    public ClienteService(){
        this.clienteDAO = new ClienteDAO();
    }

    public void cadastrar(Cliente cliente){
        clienteDAO.create(cliente);
    }

    public void atualizar(Cliente cliente){
        clienteDAO.update(cliente);
    }

    public List<Cliente> listar(){
        return clienteDAO.read();
    }

    public Cliente buscarId(int idCliente){
        return clienteDAO.findById(idCliente);
    }

    public void remover(int idCliente){
        clienteDAO.delete(idCliente);
    }
}
