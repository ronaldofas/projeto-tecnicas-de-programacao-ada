package com.ada.conta;

import com.ada.cliente.Cliente;

import java.time.LocalDateTime;

public class Transacao {

    private TipoTransacao transacao;
    private LocalDateTime dataTransacao;
    private double valor;
    private String observacao;
    private Cliente remetente;
    private Cliente destinatario;

    public Transacao(TipoTransacao transacao, double valor) {
        this.transacao = transacao;
        this.valor = valor;
        this.dataTransacao = LocalDateTime.now();
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public void setRemetente(Cliente remetente) {
        this.remetente = remetente;
    }

    public void setDestinatario(Cliente destinatario) {
        this.destinatario = destinatario;
    }

    public TipoTransacao getTransacao() {
        return transacao;
    }

    public LocalDateTime getDataTransacao() {
        return dataTransacao;
    }

    public double getValor() {
        return valor;
    }

    public String getObservacao() {
        return observacao;
    }

    public Cliente getRemetente() {
        return remetente;
    }

    public Cliente getDestinatario() {
        return destinatario;
    }
}
