package com.ada;

import com.ada.banco.BancoControlador;
import com.ada.banco.ContaRepositorio;
import com.ada.banco.ContaRepositorioList;
import com.ada.cliente.CNPJ;
import com.ada.cliente.CPF;
import com.ada.conta.NumeroConta;

public class Main {
    public static void main(String[] args) {

        ContaRepositorio contaRepositorio = new ContaRepositorioList();
        BancoControlador controlador = new BancoControlador(contaRepositorio);

        Menu menu = new Menu(controlador);
        menu.criarMenu();

    }
}