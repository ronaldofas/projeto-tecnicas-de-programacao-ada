package com.ada.util;

import com.ada.conta.Conta;

import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FiltrarVip implements Filtro{

    @Override
    public boolean filtrar(final Conta conta) {
        Objects.requireNonNull(conta, "conta nao pode ser nula");
        return conta.consultarSaldo() >= 500_000;
    }

}
