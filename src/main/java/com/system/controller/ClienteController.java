package com.system.controller;

import com.system.model.Cliente;
import com.system.service.ClienteService;

import java.util.List;

public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(){
        this.clienteService = new ClienteService();
    }

    public void cadastrarCliente(Cliente cliente){
        clienteService.cadastrar(cliente);
    }

    public void atualizarCliente(Cliente cliente){
        clienteService.atualizar(cliente);
    }

    public List<Cliente> listarClientes(){
        return clienteService.listar();
    }

    public Cliente buscarCliente(int idCliente){
        return clienteService.buscarId(idCliente);
    }

    public void removerCliente(int idCliente){

    }
}
