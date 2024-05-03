package com.ada;

import com.ada.banco.BancoControlador;
import com.ada.banco.TipoConta;
import com.ada.cliente.*;
import com.ada.conta.Conta;
import com.ada.conta.Transacao;

import java.util.List;
import java.util.Scanner;

public class Menu {



    private BancoControlador bancoController;

    public Menu(BancoControlador bancoController) {
        this.bancoController = bancoController;
    }


    // Métodos de Criação de Menu

    public void criarMenu() {
        Scanner scanner = new Scanner(System.in);
        int opcao = 0;

        do {
            System.out.println();
            System.out.println();
            System.out.println("Digite a opção desejada:");
            System.out.println("1 - Abrir Conta");
            System.out.println("2 - Depositar");
            System.out.println("3 - Sacar");
            System.out.println("4 - Transferir");
            System.out.println("5 - Consultar Saldo");
            System.out.println("6 - Listar Contas");
            System.out.println("7 - Listar Transações");
            System.out.println("8 - Sair");

            System.out.print("Opção > ");
            opcao = scanner.nextInt();
            scanner.nextLine();
            executarOpcao(opcao, scanner);

        } while (opcao != 8);

    }

    private void executarOpcao(int opcao, Scanner scanner) {
        try {
            switch (opcao) {
                case 1:
                    abrirConta(scanner);
                    break;
                case 2:
                    depositar(scanner);
                    break;
                case 3:
                    sacar(scanner);
                    break;
                case 4:
                    transferir(scanner);
                    break;
                case 5:
                    consultarSaldo(scanner);
                    break;
                case 6:
                    listarContas();
                    break;
                case 7:
                    listarTransacoes(scanner);
                    break;
                case 8:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção inválida");
                    break;
            }

        } catch (Exception e) {
            sleep(200);
            System.err.println(">>>>>>" + e.getMessage());
        }
    }



    // Métodos de interação com o usuário

    private void abrirConta(Scanner scanner) {
        Cliente cliente = criarCliente(scanner);

        System.out.print("\tDigite o tipo da conta: 1-Corrente, 2-Poupança, 3-Investimento > ");
        int tipo = scanner.nextInt();
        scanner.nextLine();
        TipoConta tipoConta = getTipoConta(tipo);

        while (tipoConta == null) {
            System.err.print("\tTipo de conta inválido. Digite novamente > ");
            tipo = scanner.nextInt();
            scanner.nextLine();
            tipoConta = getTipoConta(tipo);
        }

        String numero = bancoController.abrirConta(cliente, tipoConta);
        showMessage("Conta aberta com sucesso. Número da conta: " + numero);
    }

    private void sacar(Scanner scanner) {
        System.out.print("\tDigite o número da conta > ");
        String numero = scanner.nextLine();

        System.out.print("\tDigite o valor do saque > ");
        double valor = scanner.nextDouble();
        scanner.nextLine();

        Conta conta = bancoController.buscarConta(numero);
        bancoController.sacar(conta, valor);
        showMessage("Saque realizado com sucesso. Saldo atual: " + conta.consultarSaldo());
    }

    private void depositar(Scanner scanner) {
        System.out.print("\tDigite o número da conta > ");
        String numero = scanner.nextLine();

        System.out.print("\tDigite o valor do depósito > ");
        double valor = scanner.nextDouble();
        scanner.nextLine();

        Conta conta = bancoController.buscarConta(numero);
        bancoController.depositar(conta, valor);
        showMessage("Depósito realizado com sucesso. Saldo atual: " + conta.consultarSaldo());
    }

    private void transferir(Scanner scanner) {
        System.out.print("\tDigite o número da conta de origem > ");
        String numeroOrigem = scanner.nextLine();

        System.out.print("\tDigite o número da conta de destino > ");
        String numeroDestino = scanner.nextLine();

        System.out.print("\tDigite o valor da transferência > ");
        double valor = scanner.nextDouble();
        scanner.nextLine();

        Conta contaOrigem = bancoController.buscarConta(numeroOrigem);
        Conta contaDestino = bancoController.buscarConta(numeroDestino);
        bancoController.transferir(contaOrigem, contaDestino, valor);
        showMessage("Transferência realizada com sucesso. Saldo atual: " + contaOrigem.consultarSaldo());
    }

    private void consultarSaldo(Scanner scanner) {
        System.out.print("\tDigite o número da conta > ");
        String numero = scanner.nextLine();
        Conta conta = bancoController.buscarConta(numero);
        showMessage("Saldo atual: " + conta.consultarSaldo());
    }

    private void listarContas() {
        System.out.println("\tContas cadastradas");
        List<Conta> contas = bancoController.buscarContas();
        System.out.println("\t-------------------------------------");
        for (Conta conta : contas) {
            System.out.println("\t\tNumero: " + conta.getNumero() + " - Saldo: " + conta.consultarSaldo() + " - Cliente: " + conta.getCliente().getNome());
        }
        System.out.println("\t-------------------------------------");
    }

    private void listarTransacoes( Scanner scanner) {
        System.out.print("\tInforme o numero da conta > ");
        String numero = scanner.nextLine();
        Conta conta = bancoController.buscarConta(numero);
        List<Transacao> transacoes = conta.getTransacoes();

        System.out.println("\tTransações da conta: " + numero);
        System.out.println("\tCliente: " + conta.getCliente().getNome());
        System.out.println("\t-------------------------------------");
        for (Transacao transacao : transacoes) {
            System.out.println("\tTipo: " + transacao.getTransacao() + " - Valor: " + transacao.getValor());
        }
        System.out.println("\t-------------------------------------");
        System.out.println("\tSaldo atual: " + conta.consultarSaldo());
    }


    // Métodos auxiliares

    private static Cliente criarCliente(Scanner scanner) {

        System.out.print("\tInforme o Tipo de Cliente (PF ou PJ) > ");
        String tipoCliente = scanner.nextLine();
        Classificacao classificacao = getClassificacao(tipoCliente);
        while (classificacao == null) {
            System.err.print("\tTipo de cliente inválido. Digite novamente > ");
            tipoCliente = scanner.nextLine();
            classificacao = getClassificacao(tipoCliente);
        }

        System.out.print("\tInforme o documento do cliente > ");
        String documento = scanner.nextLine();
        Identificador<String> identificador = getIdentificador(documento, classificacao);
        while (identificador == null) {
            System.err.print("Digite novamente > ");
            documento = scanner.nextLine();
            identificador = getIdentificador(documento, classificacao);
        }

        System.out.print("\tInforme o nome do cliente > ");
        String nome = scanner.nextLine();

        Cliente cliente = new Cliente(identificador, classificacao, nome);
        return cliente;
    }

    private static TipoConta getTipoConta(int tipo) {
        switch (tipo) {
            case 1:
                return TipoConta.CORRENTE;
            case 2:
                return TipoConta.POUPANCA;
            case 3:
                return TipoConta.INVESTIMENTO;
        }
        return null;
    }

    private static Classificacao getClassificacao(String tipoCliente) {
        switch (tipoCliente) {
            case "PF":
                return Classificacao.PF;
            case "PJ":
                return Classificacao.PJ;
        }
        return null;
    }

    private static Identificador<String> getIdentificador(String documento,  Classificacao classificacao) {
        try {
            switch (classificacao) {
                case PF:
                    return new CPF(documento);
                case PJ:
                    return new CNPJ(documento);
            }
        } catch (Exception e) {
            System.err.print("\t" + e.getMessage() + ". ");
        }
        return null;
    }

    private static void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (Exception ex) {
        }
    }


    private static void showMessage(String message) {
        System.out.println();
        System.out.println("\t============================================================");
        int size = ((60 - message.length()) / 2)-1;
        System.out.print("\t ");
        for (int i = 0; i < size; i++) {
            System.out.print(" ");
        }
        System.out.print(message);
        for (int i = 0; i < size; i++) {
            System.out.print(" ");
        }
        System.out.println();
        System.out.println("\t============================================================");
    }

}
