package com.ada.conta;

import com.ada.cliente.Cliente;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
public class Transacao {

    private final TipoTransacao transacao;
    private final LocalDateTime dataTransacao;
    private final double valor;
    @Setter
    private String observacao;
    @Setter
    private Cliente remetente;
    @Setter
    private Cliente destinatario;

    public Transacao(final TipoTransacao transacao, final double valor) {
        this.transacao = transacao;
        this.valor = valor;
        this.dataTransacao = LocalDateTime.now();
    }

}
