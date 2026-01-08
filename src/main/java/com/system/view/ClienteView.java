package com.system.view;

import com.system.controller.ClienteController;
import com.system.model.Cliente;

import java.util.List;
import java.util.Scanner;

public class ClienteView {

    private final Scanner scan;
    private final ClienteController clienteController;

    public ClienteView(ClienteController clienteController){
        this.scan = new Scanner(System.in);
        this.clienteController = clienteController;
    }

    public void iniciar(){

        int opcao;

        do{
            opcao = exibirOpcoes();

            switch(opcao){
                case 1 -> lerCadastro();
                case 2 -> lerAtualizacao();
                case 3 -> exibirLista();
                case 4 -> exibirBuscaId();
                case 5 -> confirmarRemocao();
                case 0 -> System.out.println("Voltando..");
                default -> erro("opção invalida !!");
            }
        }while(opcao != 0);
    }

    public int exibirOpcoes(){

        System.out.println("=".repeat(35));
        System.out.println("GERENCIAR CLIENTES");
        System.out.println("=".repeat(35));
        System.out.println("1 - CADASTRAR CLIENTE");
        System.out.println("2 - ATUALIZAR CLIENTE");
        System.out.println("3 - LISTAR CLIENTES");
        System.out.println("4 - BUSCAR CLIENTES POR ID");
        System.out.println("5 - REMOVER CLIENTE");
        System.out.println("0 - VOLTAR");
        System.out.println("=".repeat(35));
        System.out.print("Escolha uma opcao -> ");

        return lerInteiro();
    }

    public int lerInteiro(){
        try{
            return Integer.parseInt(scan.nextLine());
        }catch(NumberFormatException e){
            return lerInteiro();
        }
    }

    public int enviarId(){
        System.out.print("\n-- Digite o ID do cliente: ");
        return lerInteiro();
    }

    public void lerCadastro(){
        System.out.println("\n--- CADASTRAR CLIENTE ---");

        System.out.print("Nome: ");
        String nome = scan.nextLine();

        System.out.println("Cpf: ");
        String cpf = scan.nextLine();

        System.out.println("Email: ");
        String email = scan.nextLine();

        try{
            Cliente cliente = new Cliente(nome,cpf,email);
            clienteController.cadastrarCliente(cliente);
            sucesso("Cliente cadastrado com sucesso!");
        }catch(NumberFormatException e){
            erro(e.getMessage());
        }
    }

    public void lerAtualizacao(){
        System.out.println("\n--- ATUALIZAR CLIENTE ---");

        int idCliente = enviarId();

        System.out.print("Novo Nome: ");
        String nome = scan.nextLine();

        System.out.println("Novo Cpf: ");
        String cpf = scan.nextLine();

        System.out.println("Novo Email: ");
        String email = scan.nextLine();

        try{
            Cliente cliente = new Cliente(nome,cpf,email);
            cliente.setIdCliente(idCliente);
            clienteController.atualizarCliente(cliente);
            sucesso("Cliente atualizado com sucesso!");
        }catch(Exception e){
            erro(e.getMessage());
        }
    }

    public void exibirLista(){
        System.out.println("\n--- LISTA DE LIVROS ---\n");

        try{
            List<Cliente> clientes = clienteController.listarClientes();

            if(clientes.isEmpty()){
                erro("Nenhum cliente encontrado !");
                return;
            }
            sucesso("Clientes encontrados!\n");
            clientes.forEach(System.out::println);
        }catch(Exception e){
            erro(e.getMessage());
        }
    }

    public void exibirBuscaId(){
        System.out.println("\n--- BUSCAR CLIENTE ---\n");

        int idCliente = enviarId();

        try{
            Cliente clienteBuscado = clienteController.buscarCliente(idCliente);
            if(clienteBuscado == null){
                erro("Nenhum cliente encontrado !");
                return;
            }

            sucesso("Cliente buscado com sucesso!");
            System.out.println("Cliente buscado: " + clienteBuscado);
        }catch(Exception e){
            erro(e.getMessage());
        }
    }

    public void confirmarRemocao(){
        System.out.println("\n--- REMOVER CLIENTE ---\n");

        int idCliente = enviarId();
        Cliente cliente = clienteController.buscarCliente(idCliente);

        try{
            if(cliente == null){
                erro("Nenhum cliente encontrado !");
                return;
            }

            System.out.print("Confirma a remoção do cliente '" + cliente.getNome() + "'? (s/n): ");
            String confirmacao = scan.nextLine();

            if(confirmacao.equalsIgnoreCase("s")){
                clienteController.removerCliente(idCliente);
            }else{
                sucesso("Operação cancelada!");
            }
        }catch(Exception e){
            erro(e.getMessage());
        }
    }

    public void erro(String mensagem){
        System.out.println("!! ERRO !! " + mensagem);
    }

    public void sucesso(String mensagem){
        System.out.println("!! SUCESSO !! " + mensagem);
    }
}
