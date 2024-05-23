package com.ada;

import com.ada.banco.BancoService;
import com.ada.banco.ContaRepositorio;
import com.ada.banco.ContaRepositorioList;

public class Main {
    public static void main(final String[] args) {

        final ContaRepositorio contaRepositorio = new ContaRepositorioList();
        final BancoService controlador = new BancoService(contaRepositorio);

        final Menu menu = new Menu(controlador);
        menu.criarMenu();

    }
}