package com.system.view;

import java.util.Scanner;

public class MenuView {

    private final Scanner scan;

    public MenuView(){
        this.scan = new Scanner(System.in);
    }

    public int exibir(){

        System.out.println("=".repeat(35));
        System.out.println("GERENCIADOR DE TROCA DE LIVROS");
        System.out.println("=".repeat(35));
        System.out.println("1 - GERENCIAR LIVROS");
        System.out.println("2 - GERENCIAR CLIENTES");
        System.out.println("3 - GERENCIAR TROCAS");
        System.out.println("0 - SAIR");
        System.out.println("=".repeat(35));
        System.out.print("Escolha uma opcao -> ");

        return lerInteiro();
    }

    public int lerInteiro(){
        try{
            return Integer.parseInt(scan.nextLine());
        }catch(NumberFormatException e){
            return -1;
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
