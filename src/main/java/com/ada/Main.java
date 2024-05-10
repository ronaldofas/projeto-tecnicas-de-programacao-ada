package com.ada;

import com.ada.banco.BancoService;
import com.ada.banco.ContaRepositorio;
import com.ada.banco.ContaRepositorioList;

public class Main {
    public static void main(String[] args) {

        ContaRepositorio contaRepositorio = new ContaRepositorioList();
        BancoService controlador = new BancoService(contaRepositorio);

        Menu menu = new Menu(controlador);
        menu.criarMenu();

    }
}