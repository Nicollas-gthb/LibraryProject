package com.system.view;

import com.system.controller.EmprestimoController;
import com.system.model.Emprestimo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class EmprestimoView {

    private final Scanner scan;
    private final EmprestimoController emprestimoController;

    public EmprestimoView(EmprestimoController emprestimoController){
        this.scan = new Scanner(System.in);
        this.emprestimoController = emprestimoController;
    }

    public void iniciar(){

        int opcao;

        do{
            opcao = exibirOpcoes();

            switch(opcao){
                case 1 -> realizar();
                case 2 -> devolver();
                case 3 -> lerAtualizacao();
                case 4 -> exibirLista();
                case 5 -> exibirBuscaId();
                case 6 -> confirmarRemocao();
                case 0 -> System.out.println("Voltando..");
                default -> erro("Opção invalida !!");
            }
        }while(opcao != 0);
    }

    public int exibirOpcoes(){

        System.out.println("=".repeat(35));
        System.out.println("GERENCIAR EMPRESTIMOS");
        System.out.println("=".repeat(35));
        System.out.println("1 - REALIZAR EMPRESTIMO");
        System.out.println("2 - DEVOLVER EMPRESTIMO");
        System.out.println("3 - ATUALIZAR EMPRESTIMO");
        System.out.println("4 - LISTAR EMPRESTIMOS");
        System.out.println("5 - BUSCAR EMPRESTIMOS POR ID");
        System.out.println("6 - REMOVER EMPRESTIMO");
        System.out.println("0 - VOLTAR");
        System.out.println("=".repeat(35));
        System.out.print("Escolha uma opcao -> ");

        return lerInteiro();
    }

    public int lerInteiro(){
        try{
            return Integer.parseInt(scan.nextLine());
        }catch(NumberFormatException e){
            erro(e.getMessage());
            return -1;
        }
    }

    public void realizar(){
        System.out.println("--- REALIZAR EMPRESTIMO ---");

        System.out.print("Informe o ID do livro: ");
        int idLivro = lerInteiro();

        System.out.print("Informe o ID do cliente: ");
        int idCliente = lerInteiro();

        try{
            Emprestimo emprestimo = new Emprestimo(idLivro, idCliente, LocalDate.now());
            emprestimoController.realizarEmprestimo(emprestimo);
            sucesso("Emprestimo realizado com sucesso!");
        } catch (Exception e) {
            erro(e.getMessage());
        }
    }

    public void devolver(){
        System.out.println("--- DEVOLVER EMPRESTIMO ---");

        int idEmprestimo = enviarId();

        try{
            emprestimoController.devolverEmprestimo(idEmprestimo);
            sucesso("Emprestimo devolvido com sucesso!");
        }catch(Exception e){
            erro(e.getMessage());
        }
    }

    public void lerAtualizacao(){
        System.out.println("\n--- ATUALIZAR EMPRESTIMO ---");

        int idEmprestimo = enviarId();

        System.out.print("Novo ID do livro: ");
        int idLivro = lerInteiro();

        System.out.print("Novo ID do cliente: ");
        int idCliente = lerInteiro();

        System.out.print("Nova data de emprestimo (dd/MM/yyyy): ");
        String dataEmprestimo = scan.nextLine();

        try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate dataConvertida = LocalDate.parse(dataEmprestimo, formatter);

            try{
                Emprestimo emprestimo = new Emprestimo(idLivro, idCliente, dataConvertida);
                emprestimoController.atualizarEmprestimo(idEmprestimo, emprestimo);
            }catch(Exception e){
                erro(e.getMessage());
            }
        }catch(DateTimeParseException e){
            erro(e.getMessage());
        }
    }

    public void exibirLista(){
        System.out.println("\n--- LISTA DE EMPRESTIMOS ---\n");

        try{
            List<Emprestimo> emprestimos = emprestimoController.listarEmprestimos();

            if(emprestimos.isEmpty()){
                erro("Nenhum emprestimo encontrado !");
                return;
            }
            sucesso("Emprestimos encontrados!");
            emprestimos.forEach(System.out::println);
        }catch(Exception e){
            erro(e.getMessage());
        }
    }

    public void exibirBuscaId(){
        System.out.println("\n--- BUSCAR EMPRESTIMO ---\n");

        int idEmprestimo = enviarId();

        try{
            Emprestimo emprestimoBuscado = emprestimoController.buscarEmprestimo(idEmprestimo);

            if(emprestimoBuscado == null){
                erro("Emprestimo no encontrado !");
                return;
            }

            sucesso("Emprestimo encontrado!");
            System.out.println(emprestimoBuscado);
        }catch(Exception e){
            erro(e.getMessage());
        }
    }

    public void confirmarRemocao(){
        System.out.println("\n--- REMOVER EMPRESTIMO ---\n");

        int idEmprestimo = enviarId();
        Emprestimo emprestimo = emprestimoController.buscarEmprestimo(idEmprestimo);

        try{
            if(emprestimo == null){
                erro("Emprestimo no encontrado !");
                return;
            }

            System.out.print("Confirma a remoção do emprestimo '" + emprestimo.getIdEmprestimo() + "'? (s/n): ");
            String confirmacao = scan.nextLine();

            if(confirmacao.equalsIgnoreCase("s")){
                emprestimoController.removerEmprestimo(idEmprestimo);
            }else{
                sucesso("Operação cancelada!");
            }

        }catch(Exception e){
            erro(e.getMessage());
        }
    }

    public int enviarId(){
        System.out.print("\n-- Digite o ID do emprestimo: ");
        return lerInteiro();
    }

    public void erro(String mensagem){
        System.out.println("!! ERRO !! " + mensagem);
    }

    public void sucesso(String mensagem){
        System.out.println("!! SUCESSO !! " + mensagem);
    }
}
