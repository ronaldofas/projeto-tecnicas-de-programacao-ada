package com.ada.banco;

import com.ada.cliente.Cliente;
import com.ada.cliente.Identificador;
import com.ada.conta.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BancoService {

    private final ContaRepositorio contaRepositorio;

    public BancoService(final ContaRepositorio contaRepositorio) {
        this.contaRepositorio = contaRepositorio;
    }

    public String abrirConta(final Cliente cliente, final TipoConta tipoConta) {

        final NumeroConta numeroConta;
        if (tipoConta.equals(TipoConta.CORRENTE)) {
            numeroConta = new NumeroConta();
            final Conta conta = new ContaCorrente(numeroConta, cliente);
            contaRepositorio.salvar(conta);

        } else if (tipoConta.equals(TipoConta.POUPANCA)) {
            numeroConta = new NumeroConta();
            final Conta conta = new ContaPoupanca(numeroConta, cliente);
            contaRepositorio.salvar(conta);

        } else if (tipoConta.equals(TipoConta.INVESTIMENTO)) {
            numeroConta = new NumeroConta();
            final Conta conta = new ContaInvestimento(numeroConta, cliente);
            contaRepositorio.salvar(conta);

        } else {
            throw new IllegalArgumentException("Tipo de conta invalida");
        }
        return numeroConta.getValor();
    }

    public void depositar(final Conta conta, final double valor) {
        conta.depositar(valor);
        final Transacao transacao = new Transacao(TipoTransacao.DEPOSITO, valor);
        conta.criarTransacao(transacao);
        contaRepositorio.atualizar(conta);
    }

    public void sacar(final Conta conta, final double valor) {
        conta.sacar(valor);
        final Transacao transacao = new Transacao(TipoTransacao.SAQUE, valor);
        conta.criarTransacao(transacao);
        contaRepositorio.atualizar(conta);
    }

    public void investir(final ContaCorrente contaCorrente, final double valor) {

        final Cliente cliente = contaCorrente.getCliente();
        final List<Conta> contas = contaRepositorio.bucarPorCliente(cliente.getIdentificador().getValor());

        Conta contaInvestimento = null;

        for (final Conta conta1 : contas) {
            if (conta1 instanceof ContaInvestimento) {
                contaInvestimento = conta1;
            }
        }

        if (contaInvestimento == null) {
            final Identificador<String> numeroConta = new NumeroConta();
            contaInvestimento = new ContaInvestimento(numeroConta, cliente);
            contaRepositorio.salvar(contaInvestimento);
        }

        contaCorrente.transferir(valor, contaInvestimento);

        final Transacao transacaoOrigem = new Transacao(TipoTransacao.INVESTIMENTO, valor);
        transacaoOrigem.setRemetente(contaCorrente.getCliente());
        transacaoOrigem.setDestinatario(contaInvestimento.getCliente());
        contaCorrente.criarTransacao(transacaoOrigem);
        contaRepositorio.atualizar(contaCorrente);

        final Transacao transacaoDestino = new Transacao(TipoTransacao.INVESTIMENTO, valor);
        transacaoDestino.setRemetente(contaCorrente.getCliente());
        transacaoDestino.setDestinatario(contaInvestimento.getCliente());
        contaInvestimento.criarTransacao(transacaoDestino);
        contaRepositorio.atualizar(contaInvestimento);
    }

    public void transferir(final Conta contaOrigem, final Conta contaDestino, final double valor) {
        contaOrigem.transferir(valor, contaDestino);

        final Transacao transacaoOrigem = new Transacao(TipoTransacao.TRANSFERENCIA, valor);
        transacaoOrigem.setRemetente(contaOrigem.getCliente());
        transacaoOrigem.setDestinatario(contaDestino.getCliente());
        contaOrigem.criarTransacao(transacaoOrigem);
        contaRepositorio.atualizar(contaOrigem);


        final Transacao transacaoDestino = new Transacao(TipoTransacao.TRANSFERENCIA, valor);
        transacaoOrigem.setRemetente(contaOrigem.getCliente());
        transacaoOrigem.setDestinatario(contaDestino.getCliente());
        contaDestino.criarTransacao(transacaoDestino);
        contaRepositorio.atualizar(contaDestino);
    }

    public List<Conta> buscarContas(){
        return contaRepositorio.buscarTodas();
    }

    public Optional<Conta> buscarConta(final String numero) {
        return contaRepositorio.buscarPorNumero(numero);
    }

    public List<Conta> buscarContasCliente(final String identificador){
        return contaRepositorio.bucarPorCliente(identificador);
    }


    public List<Conta> buscarContasVip() {
        return contaRepositorio.buscarTodas(x -> x.consultarSaldo() >= 500_000);
    }

    public List<Conta> buscarContasVarejo() {
        return contaRepositorio.buscarTodas(c -> c.consultarSaldo() < 500_00);
    }
}