package com.system.view;

import com.system.controller.LivroController;
import com.system.model.Livro;

import java.util.List;
import java.util.Scanner;

public class LivroView {

    private final Scanner scan;
    private final LivroController livroController;

    public LivroView(LivroController livroController) {
        this.scan = new  Scanner(System.in);
        this.livroController = livroController;
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
                case 0 -> System.out.println("Voltando");
                default -> erro("opção invalida !!");
            }
        }while(opcao != 0);
    }

    public int exibirOpcoes(){

        System.out.println("=".repeat(35));
        System.out.println("GERENCIAR LIVROS");
        System.out.println("=".repeat(35));
        System.out.println("1 - CADASTRAR LIVRO");
        System.out.println("2 - ATUALIZAR LIVRO");
        System.out.println("3 - LISTAR LIVROS");
        System.out.println("4 - BUSCAR LIVROS POR ID");
        System.out.println("5 - REMOVER LIVRO");
        System.out.println("0 - VOLTAR");
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

    public void lerCadastro(){
        System.out.println("\n--- CADASTRAR LIVRO ---");

        System.out.print("Nome do livro: ");
        String titulo = scan.nextLine();

        System.out.println("Autor: ");
        String autor = scan.nextLine();

        System.out.println("Ano: ");
        int ano = lerInteiro();

        try{
            Livro livro = new Livro(titulo, autor, ano);
            livroController.cadastrarLivro(livro);
            sucesso("Livro foi cadastrado !\n");
        }catch(Exception e){
            erro(e.getMessage());
        }
    }

    public void lerAtualizacao(){
        System.out.println("\n--- ATUALIZAR LIVRO ---");

        int idLivro = enviarId();

        System.out.print("Novo Nome do livro: ");
        String titulo = scan.nextLine();

        System.out.print("Novo Autor: ");
        String autor = scan.nextLine();

        System.out.print("Novo Ano: ");
        int ano = lerInteiro();

        try{
            Livro livro = new Livro(titulo, autor, ano);
            livro.setIdLivro(idLivro);
            livroController.atualizarLivro(livro);
            sucesso("Livro foi atualizado !\n");
        }catch(Exception e){
            erro(e.getMessage());
        }
    }

    public void exibirLista(){
        System.out.println("\n--- LISTA DE LIVROS ---\n");

        try{
            List<Livro> livros = livroController.listarLivro();

            if(livros.isEmpty()){
                System.out.println("Nenhum livro encontrado!");
                return;
            }
            sucesso("Livros encontrados!\n");
            livros.forEach(System.out::println);
        }catch(Exception e) {
            erro(e.getMessage());
        }
    }

    public int enviarId(){
        System.out.print("\n-- Digite o ID do livro: ");
        return lerInteiro();
    }

    public void exibirBuscaId(){
        System.out.println("\n--- BUSCA POR ID ---\n");

        int Idlivro = enviarId();
        try{
            Livro livroBuscado = livroController.buscarLivro(Idlivro);
            if(livroBuscado == null){
                erro("Livro não encontrado !");
                return;
            }
            sucesso("Livro foi buscado !\n");
            System.out.println("Livro buscado: " + livroBuscado);
        }catch(Exception e){
            erro(e.getMessage());
        }
    }

    public void confirmarRemocao(){
        System.out.println("\n--- REMOVER LIVRO ---\n");

        int idLivro = enviarId();
        Livro livro = livroController.buscarLivro(idLivro);

        if(livro == null){
            erro("Nenhum livro encontrado !");
            return;
        }
        System.out.print("Confirma a remoção do livro '" + livro.getTitulo() + "'? (s/n): ");
        String confirmacao = scan.nextLine();

        if(confirmacao.equalsIgnoreCase("s")){
            livroController.excluirLivro(idLivro);
        }else{
            sucesso("Operação cancelada !");
        }
    }

    public void erro(String mensagem){
        System.out.println("!! ERRO !! " + mensagem);
    }

    public void sucesso(String mensagem){
        System.out.println("!! SUCESSO !! " + mensagem);
    }
}
