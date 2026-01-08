package com.system;

import com.system.view.MenuView;

public class Main {
    public static void main(String[] args) {

        MenuView menuView = new MenuView();

        menuView.iniciarMenu();
        menuView.fecharScanner();
    }
}