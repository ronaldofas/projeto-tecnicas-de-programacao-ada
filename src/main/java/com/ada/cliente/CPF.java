package com.ada.cliente;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
public class CPF implements Identificador<String> {

    private String valor;

    public CPF(String valor) {
        this.valor = valor;
        this.validar();
    }

    @Override
    public String getValor() {
        return valor;
    }

    @Override
    public void validar() {
        if (this.valor == null || this.valor.length() != 11){
            throw new IllegalArgumentException("CPF inválido");
        }
    }
}
