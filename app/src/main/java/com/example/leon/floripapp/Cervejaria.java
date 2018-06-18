package com.example.leon.floripapp;

import java.io.Serializable;

public class Cervejaria implements Serializable {

    private int id;
    private String nome;
    private String localizacao;
    private String descricao;
    private String dataFuncionamento;
    private String horarioFuncionamento;
    private boolean favorito;

    public boolean isFavorito() {
        return favorito;
    }

    public void setFavorito(boolean favorito) {
        this.favorito = favorito;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDataFuncionamento() {
        return dataFuncionamento;
    }

    public void setDataFuncionamento(String dataFuncionamento) {
        this.dataFuncionamento = dataFuncionamento;
    }

    public String getHorarioFuncionamento() {
        return horarioFuncionamento;
    }

    public void setHorarioFuncionamento(String horarioFuncionamento) {
        this.horarioFuncionamento = horarioFuncionamento;
    }

    @Override
    public String toString() {
        return getNome();
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }
}
