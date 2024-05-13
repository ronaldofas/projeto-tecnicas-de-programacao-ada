package com.ada.cliente;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class CNPJ implements Identificador<String> {

    private String valor;

    public CNPJ(String valor) {
        this.valor = valor;
        this.validar();
    }

    @Override
    public String getValor() {
        return valor;
    }

    @Override
    public void validar() {
        if (this.valor == null || this.valor.length() != 14){
            throw new IllegalArgumentException("CNPJ inválido");
        }
    }
}
