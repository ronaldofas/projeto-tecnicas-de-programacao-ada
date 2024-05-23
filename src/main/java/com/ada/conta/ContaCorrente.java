package com.ada.conta;

import com.ada.cliente.Classificacao;
import com.ada.cliente.Cliente;
import com.ada.cliente.Identificador;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ContaCorrente implements Conta {

    private final Identificador<String> numeroConta;
    private double saldo;
    private final Cliente cliente;
    private final LocalDateTime dataAtualizacao;
    private List<Transacao> transacoes = new ArrayList<>();
    private boolean status;

    public ContaCorrente(final Identificador<String> numeroConta, final Cliente cliente) {
        this.numeroConta = numeroConta;
        this.cliente = cliente;
        this.dataAtualizacao = LocalDateTime.now();
        this.status = true;
    }


    @Override
    public Cliente getCliente() {
        return cliente;
    }

    @Override
    public String getNumero() {
        return numeroConta.getValor();
    }

    @Override
    public List<Transacao> getTransacoes() {
        return transacoes;
    }

    @Override
    public double consultarSaldo() {
        return this.saldo;
    }

    @Override
    public void sacar(final double valor) {
        if (this.saldo < valor){
            throw new IllegalArgumentException("Saldo isuficiente");
        }

        double taxaAhCobrar = 0;

        if (this.cliente.getClassificacao().equals(Classificacao.PJ)){
            taxaAhCobrar = valor * 0.005;
        }

        this.saldo -= (valor + taxaAhCobrar);
    }

    @Override
    public void depositar(final double valor) {
        if (valor <= 0){
            throw new IllegalArgumentException("O valor do deposito deve ser maior que zero");
        }
        this.saldo += valor;
    }

    @Override
    public void transferir(final double valor, final Conta contaDestino) {
        if (this.saldo < valor){
            throw new IllegalArgumentException("Saldo isuficiente");
        }

        double taxaAhCobrar = 0;

        if (this.cliente.getClassificacao().equals(Classificacao.PJ)){
            taxaAhCobrar = valor * 0.005;
        }

        this.saldo -= (valor + taxaAhCobrar);
        contaDestino.depositar(valor);
    }

    @Override
    public void criarTransacao(final Transacao transacao) {
        transacoes.add(transacao);
    }
}
