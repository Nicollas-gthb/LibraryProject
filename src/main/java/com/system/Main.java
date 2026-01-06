package com.system;

import com.system.controller.LivroController;
import com.system.view.LivroView;
import com.system.view.MenuView;

public class Main {
    public static void main(String[] args) {

        MenuView menuView = new MenuView();
        LivroController livroController = new LivroController();
        LivroView livroView = new LivroView(livroController);

        int opcao;

        do{
            opcao = menuView.exibirOpcoes();

            switch(opcao){
                case 1 -> livroView.iniciar();
                case 0 -> menuView.encerrar();
                default -> menuView.erro("Opção inválida !!");
            }
        }while(opcao != 0);

        menuView.encerrar();
    }
}