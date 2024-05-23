package com.ada.util;

import com.ada.conta.Conta;

import java.util.List;
import java.util.function.Predicate;

@FunctionalInterface
public interface Filtro {

    boolean filtrar(Conta conta);

}
