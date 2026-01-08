package com.system.view;

import com.system.controller.ClienteController;
import com.system.controller.EmprestimoController;
import com.system.controller.LivroController;

import java.util.Scanner;

public class MenuView {

    private final Scanner scan;

    private final LivroController livroController = new LivroController();
    private final LivroView livroView = new LivroView(livroController);

    private final ClienteController clienteController = new ClienteController();
    private final ClienteView clienteView = new ClienteView(clienteController);

    private final EmprestimoController emprestimoController = new EmprestimoController();
    private final EmprestimoView emprestimoView = new EmprestimoView(emprestimoController);

    public MenuView(){
        this.scan = new Scanner(System.in);
    }

    public void iniciarMenu(){
        int opcao;

        do{
            opcao = exibirOpcoes();

            switch(opcao){
                case 1 -> livroView.iniciar();
                case 2 -> clienteView.iniciar();
                case 3 -> emprestimoView.iniciar();
                case 0 -> encerrar();
                default -> erro("Opção inválida !!");
            }
        }while(opcao != 0);
    }

    public int exibirOpcoes(){

        System.out.println("=".repeat(35));
        System.out.println("GERENCIADOR DE TROCA DE LIVROS");
        System.out.println("=".repeat(35));
        System.out.println("1 - GERENCIAR LIVROS");
        System.out.println("2 - GERENCIAR CLIENTES");
        System.out.println("3 - GERENCIAR EMPRESTIMOS");
        System.out.println("0 - SAIR");
        System.out.println("=".repeat(35));
        System.out.print("Escolha uma opcao -> ");

        return lerInteiro();
    }

    public int lerInteiro(){
        try{
            return Integer.parseInt(scan.nextLine());
        }catch(NumberFormatException e){
            erro("Opção invalida !!");
            return lerInteiro();
        }
    }

    public void erro(String mensagem){
        System.out.println("!! ERRO !! " + mensagem);
    }

    public void encerrar(){
        System.out.println("\n x_x Encerrando o programa...");
    }

    public void fecharScanner(){
        scan.close();
    }
}
