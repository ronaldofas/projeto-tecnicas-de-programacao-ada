package com.ada.banco;

import com.ada.conta.Conta;

import java.util.List;

public interface ContaRepositorio {

    void salvar(Conta conta);

    void atualizar(Conta conta);

    Conta buscarPorNumero(String numero);

    List<Conta> bucarPorCliente(String identificador);

    List<Conta> buscarTodas();

}
