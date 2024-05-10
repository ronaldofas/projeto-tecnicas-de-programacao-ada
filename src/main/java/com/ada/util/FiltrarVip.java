package com.ada.util;

import com.ada.conta.Conta;

import java.util.Objects;

public class FiltrarVip implements Filtro{

    @Override
    public boolean filtrar(final Conta conta) {
        Objects.requireNonNull(conta, "conta nao pode ser nula");
        return conta.consultarSaldo() >= 500_000;
    }

}
