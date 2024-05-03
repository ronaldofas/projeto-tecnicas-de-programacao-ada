package com.ada.cliente;

import java.time.LocalDate;

public class Cliente {

    private Identificador<String> identificador;
    private Classificacao classificacao;
    private String nome;
    private LocalDate dataCadastro;
    private boolean status;

    public Cliente(Identificador<String> identificador, Classificacao classificacao, String nome) {
        this.identificador = identificador;
        this.classificacao = classificacao;
        this.nome = nome;
        this.dataCadastro = LocalDate.now();
        this.status = true;
        this.validar();
    }

    public void alterarNome(String novoNome){
        if (novoNome == null || novoNome.isBlank()){
            throw new IllegalArgumentException("O nome não pode ser vazio");
        }
        this.nome = novoNome;
    }

    public void ativarDesativar(){
        this.status = !this.status;
    }

    public Identificador<String> getIdentificador() {
        return identificador;
    }

    public Classificacao getClassificacao() {
        return classificacao;
    }

    public String getNome() {
        return nome;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public boolean isStatus() {
        return status;
    }

    public void validar(){
        if (this.nome == null || this.nome.isBlank()){
            throw new IllegalArgumentException("O nome não pode ser vazio");
        }
        if (this.identificador == null){
            throw new IllegalArgumentException("O identificador não pode ser nulo");
        }
        if (this.classificacao == null){
            throw new IllegalArgumentException("A classificação não pode ser nula");
        }
    }

}
