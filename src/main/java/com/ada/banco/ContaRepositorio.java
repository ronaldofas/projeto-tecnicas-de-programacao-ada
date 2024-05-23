package com.ada.banco;

import com.ada.conta.Conta;
import com.ada.util.Filtro;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public interface ContaRepositorio {

    void salvar(Conta conta);

    void atualizar(Conta conta);

    Optional<Conta> buscarPorNumero(String numero);

    List<Conta> bucarPorCliente(String identificador);

    List<Conta> buscarTodas();

    List<Conta> buscarTodas(Predicate<Conta> predicate);
}
