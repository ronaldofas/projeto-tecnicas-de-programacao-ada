package com.ada.conta;

import com.ada.cliente.Cliente;

import java.util.List;

public interface Conta {

    Cliente getCliente();

    String getNumero();

    List<Transacao> getTransacoes();

    void sacar(double valor);

    void depositar(double valor);

    void transferir(double valor, Conta contaDestino);

    double consultarSaldo();

    void criarTransacao(Transacao transacao);

}
