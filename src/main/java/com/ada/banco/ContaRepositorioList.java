package com.ada.banco;

import com.ada.conta.Conta;
import com.ada.util.Filtro;

import java.util.ArrayList;
import java.util.List;

public class ContaRepositorioList implements ContaRepositorio {

    List<Conta> contas = new ArrayList<>();

    @Override
    public void salvar(Conta conta) {
        this.contas.add(conta);
    }

    @Override
    public void atualizar(Conta conta) {

        for (int i = 0; i < contas.size(); i++) {
            if (contas.get(i).getNumero().equals(conta.getNumero())) {
                contas.set(i, conta);
            }
        }

    }

    @Override
    public Conta buscarPorNumero(String numero) {
        for (Conta conta : contas) {
            if (conta.getNumero().equals(numero)) {
                return conta;
            }
        }
        return null;
    }

    @Override
    public List<Conta> bucarPorCliente(String identificador) {
        List<Conta> contasCliente = new ArrayList<>();

        for (Conta conta : contas) {
            if (conta.getCliente().getIdentificador().getValor().equals(identificador)) {
                contasCliente.add(conta);
            }
        }

        return contasCliente;
    }

    @Override
    public List<Conta> buscarTodas() {
        return contas;
    }

    @Override
    public List<Conta> buscarTodas(final Filtro filtro) {
        final List<Conta> contasFiltradas = new ArrayList<>();

        for (Conta conta : contas) {
            if (filtro.filtrar(conta)) {
                contasFiltradas.add(conta);
            }
        }

        return contasFiltradas;
    }
}
