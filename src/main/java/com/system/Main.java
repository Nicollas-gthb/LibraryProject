package com.system;

import com.system.controller.ClienteController;
import com.system.controller.EmprestimoController;
import com.system.controller.LivroController;
import com.system.view.ClienteView;
import com.system.view.EmprestimoView;
import com.system.view.LivroView;
import com.system.view.MenuView;

public class Main {
    public static void main(String[] args) {

        MenuView menuView = new MenuView();

        LivroController livroController = new LivroController();
        LivroView livroView = new LivroView(livroController);

        ClienteController clienteController = new ClienteController();
        ClienteView clienteView = new ClienteView(clienteController);

        EmprestimoController emprestimoController = new EmprestimoController();
        EmprestimoView emprestimoView = new EmprestimoView(emprestimoController);

        int opcao;

        do{
            opcao = menuView.exibirOpcoes();

            switch(opcao){
                case 1 -> livroView.iniciar();
                case 2 -> clienteView.iniciar();
                case 3 -> emprestimoView.iniciar();
                case 0 -> menuView.encerrar();
                default -> menuView.erro("Opção inválida !!");
            }
        }while(opcao != 0);

        menuView.encerrar();
        menuView.fecharScanner();
    }
}