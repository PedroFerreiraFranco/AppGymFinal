package br.edu.ifsuldeminas.mch.championsgym.model;

import java.io.Serializable;

public class Treino implements Serializable {
    private Integer id;
    private String nomeExercicio;
    private String duracao;
    private String dataTreino;

    public Treino(int id, String nomeExercicio, String duracao, String dataTreino) {
        this.id = id;
        this.nomeExercicio = nomeExercicio;
        this.duracao = duracao;
        this.dataTreino = dataTreino;
    }

    // Getters e Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomeExercicio() {
        return nomeExercicio;
    }

    public void setNomeExercicio(String nomeExercicio) {
        this.nomeExercicio = nomeExercicio;
    }

    public String getDuracao() {
        return duracao;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }

    public String getDataTreino() {
        return dataTreino;
    }

    public void setDataTreino(String dataTreino) {
        this.dataTreino = dataTreino;
    }

    @Override
    public String toString() {
        return String.format("Treino: [ID= %d, Nome do Exercício= %s, Duração= %s, Data do Treino= %s]",
                id, nomeExercicio, duracao, dataTreino);
    }
}
