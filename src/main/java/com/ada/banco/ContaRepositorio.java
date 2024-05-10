package com.ada.banco;

import com.ada.conta.Conta;
import com.ada.util.FiltrarVip;
import com.ada.util.Filtro;

import java.util.List;

public interface ContaRepositorio {

    void salvar(Conta conta);

    void atualizar(Conta conta);

    Conta buscarPorNumero(String numero);

    List<Conta> bucarPorCliente(String identificador);

    List<Conta> buscarTodas();

    List<Conta> buscarTodas(Filtro filtro);
}
