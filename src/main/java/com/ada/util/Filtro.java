package com.ada.util;

import com.ada.conta.Conta;

@FunctionalInterface
public interface Filtro {

    boolean filtrar(Conta conta);

}
