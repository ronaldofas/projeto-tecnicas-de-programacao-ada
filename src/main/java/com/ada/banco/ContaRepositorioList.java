package com.ada.banco;

import com.ada.conta.Conta;
import com.ada.util.Filtro;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ContaRepositorioList implements ContaRepositorio {

    final List<Conta> contas = new ArrayList<>();

    @Override
    public void salvar(final Conta conta) {
        this.contas.add(conta);
    }

    @Override
    public void atualizar(final Conta conta) {

        for (int i = 0; i < contas.size(); i++) {
            if (contas.get(i).getNumero().equals(conta.getNumero())) {
                contas.set(i, conta);
            }
        }
    }

    @Override
    public Optional<Conta> buscarPorNumero(final String numero) {
        for (final Conta conta : contas) {
            if (conta.getNumero().equals(numero)) {
                return Optional.of(conta);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Conta> bucarPorCliente(final String identificador) {
        final List<Conta> contasCliente = new ArrayList<>();

        for (final Conta conta : contas) {
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
    public List<Conta> buscarTodas(final Predicate<Conta> predicate) {
        return contas.stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }
}
