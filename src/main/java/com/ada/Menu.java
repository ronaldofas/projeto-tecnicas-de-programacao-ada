package com.ada;

import com.ada.banco.BancoService;
import com.ada.banco.TipoConta;
import com.ada.cliente.*;
import com.ada.conta.Conta;
import com.ada.conta.Transacao;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Menu {

    private final BancoService bancoService;

    public Menu(final BancoService bancoService) {
        this.bancoService = bancoService;
    }


    // Métodos de Criação de Menu

    public void criarMenu() {
        final Scanner scanner = new Scanner(System.in);
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
            System.out.println("8 - Listar Contas VIP");
            System.out.println("9 - Listar Contas Varejo");
            System.out.println("99 - Sair");

            System.out.print("Opção > ");
            opcao = scanner.nextInt();
            scanner.nextLine();
            executarOpcao(opcao, scanner);

        } while (opcao != 99);

    }

    private void executarOpcao(final int opcao, final Scanner scanner) {
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
                    listarContasVip();
                    break;
                case 9:
                    listarContasVarejo();
                    break;
                case 99:
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

    private void listarContasVarejo() {
        final List<Conta> contas = bancoService.buscarContasVarejo();
        System.out.println("\tContas cadastradas Varejo");
        System.out.println("\t-------------------------------------");
        contas.forEach(conta -> {
            System.out.println("\t\tNumero: " + conta.getNumero() +
                    " - Saldo: " + conta.consultarSaldo() +
                    " - Cliente: " + conta.getCliente().getNome());
        });
        System.out.println("\t-------------------------------------");
    }

    private void listarContasVip() {
        final List<Conta> contas = bancoService.buscarContasVip();
        System.out.println("\tContas cadastradas Vip");
        System.out.println("\t-------------------------------------");
        contas.forEach(conta -> {
            System.out.println("\t\tNumero: " + conta.getNumero() +
                    " - Saldo: " + conta.consultarSaldo() +
                    " - Cliente: " + conta.getCliente().getNome());
        });
        System.out.println("\t-------------------------------------");
    }


    // Métodos de interação com o usuário

    private void abrirConta(final Scanner scanner) {
        final Cliente cliente = criarCliente(scanner);

        System.out.print("\tDigite o tipo da conta: 1-Corrente, 2-Poupança, 3-Investimento > ");
        int tipo = scanner.nextInt();
        scanner.nextLine();
        Optional<TipoConta> tipoConta = getTipoConta(tipo);

        while (tipoConta.isEmpty()) {
            System.err.print("\tTipo de conta inválido. Digite novamente > ");
            tipo = scanner.nextInt();
            scanner.nextLine();
            tipoConta = getTipoConta(tipo);
        }

        final String numero = bancoService.abrirConta(cliente, tipoConta.get());
        showMessage("Conta aberta com sucesso. Número da conta: " + numero);
    }

    private void sacar(final Scanner scanner) {
        System.out.print("\tDigite o número da conta > ");
        final String numero = String.format("%06d", Integer.parseInt(scanner.nextLine()));

        System.out.print("\tDigite o valor do saque > ");
        final double valor = scanner.nextDouble();
        scanner.nextLine();

        final Optional<Conta> conta = bancoService.buscarConta(numero);
        if (conta.isEmpty()){
            throw new IllegalArgumentException("Conta não localizada!");
        } else {
            bancoService.sacar(conta.get(), valor);
            showMessage("Saque realizado com sucesso. Saldo atual: " + conta.get().consultarSaldo());
        }
    }

    private void depositar(final Scanner scanner) {
        System.out.print("\tDigite o número da conta > ");
        final String numero = String.format("%06d", Integer.parseInt(scanner.nextLine()));

        System.out.print("\tDigite o valor do depósito > ");
        final double valor = scanner.nextDouble();
        scanner.nextLine();

        final Optional<Conta> conta = bancoService.buscarConta(numero);
        if (conta.isEmpty()){
            throw new IllegalArgumentException("Conta não localizada!");
        } else {
            bancoService.depositar(conta.get(), valor);
            showMessage("Depósito realizado com sucesso. Saldo atual: " + conta.get().consultarSaldo());
        }
    }

    private void transferir(final Scanner scanner) {
        System.out.print("\tDigite o número da conta de origem > ");
        final String numeroOrigem = String.format("%06d", Integer.parseInt(scanner.nextLine()));

        System.out.print("\tDigite o número da conta de destino > ");
        final String numeroDestino = String.format("%06d", Integer.parseInt(scanner.nextLine()));;

        System.out.print("\tDigite o valor da transferência > ");
        final double valor = scanner.nextDouble();
        scanner.nextLine();

        final Optional<Conta> contaOrigem = bancoService.buscarConta(numeroOrigem);
        if (contaOrigem.isEmpty()){
            throw new IllegalArgumentException("Conta origem não localizada!");
        }
        final Optional<Conta> contaDestino = bancoService.buscarConta(numeroDestino);
        if (contaDestino.isEmpty()){
            throw new IllegalArgumentException("Conta origem não localizada!");
        } else {
            bancoService.transferir(contaOrigem.get(), contaDestino.get(), valor);
            showMessage("Transferência realizada com sucesso. Saldo atual: "
                    + contaOrigem.get().consultarSaldo());
        }
    }

    private void consultarSaldo(final Scanner scanner) {
        System.out.print("\tDigite o número da conta > ");
        final String numero = String.format("%06d", Integer.parseInt(scanner.nextLine()));
        final Optional<Conta> conta = bancoService.buscarConta(numero);
        if (conta.isEmpty()){
            throw new IllegalArgumentException("Conta não localizada!");
        }
        showMessage("Saldo atual: " + conta.get().consultarSaldo());
    }

    private void listarContas() {
        System.out.println("\tContas cadastradas");
        final List<Conta> contas = bancoService.buscarContas();
        System.out.println("\t-------------------------------------");
        for (final Conta conta : contas) {
            System.out.println("\t\tNumero: " + conta.getNumero() + " - Saldo: " + conta.consultarSaldo() + " - Cliente: " + conta.getCliente().getNome());
        }
        System.out.println("\t-------------------------------------");
    }

    private void listarTransacoes(final Scanner scanner) {
        System.out.print("\tInforme o numero da conta > ");
        final String numero = String.format("%06d", Integer.parseInt(scanner.nextLine()));
        final Optional<Conta> conta = bancoService.buscarConta(numero);
        if (conta.isEmpty()){
            throw new IllegalArgumentException("Conta não localizada!");
        }
        final List<Transacao> transacoes = conta.get().getTransacoes();

        System.out.println("\tTransações da conta: " + numero);
        System.out.println("\tCliente: " + conta.get().getCliente().getNome());
        System.out.println("\t-------------------------------------");
        for (final Transacao transacao : transacoes) {
            System.out.println("\tTipo: " + transacao.getTransacao() + " - Valor: " + transacao.getValor());
        }
        System.out.println("\t-------------------------------------");
        System.out.println("\tSaldo atual: " + conta.get().consultarSaldo());
    }


    // Métodos auxiliares

    private static Cliente criarCliente(final Scanner scanner) {

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
        final String nome = scanner.nextLine();

        return new Cliente(identificador, classificacao, nome);
    }

    private static Optional<TipoConta> getTipoConta(final int tipo) {
        switch (tipo) {
            case 1:
                return Optional.of(TipoConta.CORRENTE);
            case 2:
                return Optional.of(TipoConta.POUPANCA);
            case 3:
                return Optional.of(TipoConta.INVESTIMENTO);
        }
        return Optional.empty();
    }

    private static Classificacao getClassificacao(final String tipoCliente) {
        switch (tipoCliente) {
            case "PF":
                return Classificacao.PF;
            case "PJ":
                return Classificacao.PJ;
        }
        return null;
    }

    private static Identificador<String> getIdentificador(final String documento, final Classificacao classificacao) {
        try {
            switch (classificacao) {
                case PF:
                    return new CPF(documento);
                case PJ:
                    return new CNPJ(documento);
            }
        } catch (final Exception e) {
            System.err.print("\t" + e.getMessage() + ". ");
        }
        return null;
    }

    private static void sleep(final int time) {
        try {
            Thread.sleep(time);
        } catch (final Exception ex) {
        }
    }


    private static void showMessage(final String message) {
        System.out.println();
        System.out.println("\t============================================================");
        final int size = ((60 - message.length()) / 2)-1;
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
