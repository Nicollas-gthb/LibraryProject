package com.system;

import com.system.controller.ClienteController;
import com.system.controller.EmprestimoController;
import com.system.controller.LivroController;
import com.system.view.MenuView;

public class Main {
    public static void main(String[] args) {

        MenuView menuView = new MenuView();
        LivroController livroController =  new LivroController();
        ClienteController clienteController = new ClienteController();
        EmprestimoController emprestimoController = new EmprestimoController();

        int opcao;

        do{
            opcao = menuView.exibirOpcoes();

            switch(opcao){
                case 1 -> ;
                case 2 -> ;
                case 3 -> ;
                case 0 -> menuView.encerrar();
                default -> menuView.erro("Opção inválida !!");
            }
        }while(opcao != 0);

        menuView.encerrar();
    }
}