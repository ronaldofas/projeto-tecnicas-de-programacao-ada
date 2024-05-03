package com.ada.conta;

import com.ada.cliente.Classificacao;
import com.ada.cliente.Cliente;
import com.ada.cliente.Identificador;

import java.time.LocalDateTime;
import java.util.List;

public class ContaInvestimento implements Conta {

    private Identificador<String> numeroConta;
    private double saldo;
    private Cliente cliente;
    private LocalDateTime dataAtualizacao;
    private List<Transacao> transacoes;
    private boolean status;

    public ContaInvestimento(Identificador<String> numeroConta, Cliente cliente) {
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
    public void sacar(double valor) {
        if (this.saldo < valor){
            throw new IllegalArgumentException("Saldo insuficiente");
        }
        this.saldo -= valor;
    }

    @Override
    public void depositar(double valor) {
        if (valor <= 0){
            throw new IllegalArgumentException("Valor do depósito deve ser maior que zero");
        }
        this.saldo += valor;
    }

    @Override
    public void transferir(double valor, Conta contaDestino) {
        if (this.saldo < valor){
            throw new IllegalArgumentException("Saldo insuficiente");
        }
        this.saldo -= valor;
        contaDestino.depositar(valor);
    }

    @Override
    public double consultarSaldo() {
        return this.saldo;
    }

    @Override
    public void criarTransacao(Transacao transacao) {
        this.transacoes.add(transacao);
    }

    public List<Transacao> getTransacoes() {
        return transacoes;
    }

    public void aplicarRendimento(){
        if (this.getCliente().getClassificacao().equals(Classificacao.PJ)){
            this.saldo += this.saldo * 0.005;
        } else {
            this.saldo += this.saldo * 0.001;
        }
    }


}
