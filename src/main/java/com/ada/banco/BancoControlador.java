package com.ada.banco;

import com.ada.cliente.Cliente;
import com.ada.cliente.Identificador;
import com.ada.conta.*;

import java.util.List;

public class BancoControlador {

    private ContaRepositorio contaRepositorio;

    public BancoControlador(ContaRepositorio contaRepositorio) {
        this.contaRepositorio = contaRepositorio;
    }

    public String abrirConta(Cliente cliente, TipoConta tipoConta) {

        NumeroConta numeroConta;
        if (tipoConta.equals(TipoConta.CORRENTE)) {
            numeroConta = new NumeroConta();
            Conta conta = new ContaCorrente(numeroConta, cliente);
            contaRepositorio.salvar(conta);

        } else if (tipoConta.equals(TipoConta.POUPANCA)) {
            numeroConta = new NumeroConta();
            Conta conta = new ContaPoupanca(numeroConta, cliente);
            contaRepositorio.salvar(conta);

        } else if (tipoConta.equals(TipoConta.INVESTIMENTO)) {
            numeroConta = new NumeroConta();
            Conta conta = new ContaInvestimento(numeroConta, cliente);
            contaRepositorio.salvar(conta);

        } else {
            throw new IllegalArgumentException("Tipo de conta invalida");
        }
        return numeroConta.getValor();
    }

    public void depositar(Conta conta, double valor) {
        conta.depositar(valor);
        Transacao transacao = new Transacao(TipoTransacao.DEPOSITO, valor);
        conta.criarTransacao(transacao);
        contaRepositorio.atualizar(conta);
    }

    public void sacar(Conta conta, double valor) {
        conta.sacar(valor);
        Transacao transacao = new Transacao(TipoTransacao.SAQUE, valor);
        conta.criarTransacao(transacao);
        contaRepositorio.atualizar(conta);
    }

    public void investir(ContaCorrente contaCorrente, double valor) {

        Cliente cliente = contaCorrente.getCliente();
        List<Conta> contas = contaRepositorio.bucarPorCliente(cliente.getIdentificador().getValor());

        Conta contaInvestimento = null;

        for (Conta conta1 : contas) {
            if (conta1 instanceof ContaInvestimento) {
                contaInvestimento = conta1;
            }
        }

        if (contaInvestimento == null) {
            Identificador<String> numeroConta = new NumeroConta();
            contaInvestimento = new ContaInvestimento(numeroConta, cliente);
            contaRepositorio.salvar(contaInvestimento);
        }

        contaCorrente.transferir(valor, contaInvestimento);

        Transacao transacaoOrigem = new Transacao(TipoTransacao.INVESTIMENTO, valor);
        transacaoOrigem.setRemetente(contaCorrente.getCliente());
        transacaoOrigem.setDestinatario(contaInvestimento.getCliente());
        contaCorrente.criarTransacao(transacaoOrigem);
        contaRepositorio.atualizar(contaCorrente);

        Transacao transacaoDestino = new Transacao(TipoTransacao.INVESTIMENTO, valor);
        transacaoDestino.setRemetente(contaCorrente.getCliente());
        transacaoDestino.setDestinatario(contaInvestimento.getCliente());
        contaInvestimento.criarTransacao(transacaoDestino);
        contaRepositorio.atualizar(contaInvestimento);
    }

    public void transferir(Conta contaOrigem, Conta contaDestino, double valor) {
        contaOrigem.transferir(valor, contaDestino);

        Transacao transacaoOrigem = new Transacao(TipoTransacao.TRANSFERENCIA, valor);
        transacaoOrigem.setRemetente(contaOrigem.getCliente());
        transacaoOrigem.setDestinatario(contaDestino.getCliente());
        contaOrigem.criarTransacao(transacaoOrigem);
        contaRepositorio.atualizar(contaOrigem);


        Transacao transacaoDestino = new Transacao(TipoTransacao.TRANSFERENCIA, valor);
        transacaoOrigem.setRemetente(contaOrigem.getCliente());
        transacaoOrigem.setDestinatario(contaDestino.getCliente());
        contaDestino.criarTransacao(transacaoDestino);
        contaRepositorio.atualizar(contaDestino);
    }

    public List<Conta> buscarContas(){
        return contaRepositorio.buscarTodas();
    }

    public Conta buscarConta(String numero) {
        Conta conta = contaRepositorio.buscarPorNumero(numero);
        if (conta == null) {
            throw new IllegalArgumentException("Conta "+numero+" não encontrada");
        }
        return conta;
    }

    public List<Conta> buscarContasCliente(String identificador){
        return contaRepositorio.bucarPorCliente(identificador);
    }







}