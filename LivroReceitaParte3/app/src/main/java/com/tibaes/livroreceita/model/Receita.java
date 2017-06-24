package com.tibaes.livroreceita.model;

import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;

/**
 * Created by Juliana Tibães on 05/06/2017.
 * LivroReceita
 */

@IgnoreExtraProperties
public class Receita implements Serializable{
    private String id;
    private String nome;
    private String ingredientes;
    private String modoPreparo;
    private Integer tempoPreparo;
    private Float nivelDificuldade;
    private Integer quantidadePorcoes;
    private String caminhoFoto;

    public Receita() {
    }

    public Receita(String nome, Float nivelDificuldade) {
        this.nome = nome;
        this.nivelDificuldade = nivelDificuldade;
    }

    public Receita(String id, String nome, Float nivelDificuldade) {
        this.id = id;
        this.nome = nome;
        this.nivelDificuldade = nivelDificuldade;
    }

    public Receita(String id, String nome, String ingredientes, String modoPreparo, Integer tempoPreparo, Float nivelDificuldade, Integer quantidadePorcoes, String caminhoFoto) {
        this.id = id;
        this.nome = nome;
        this.ingredientes = ingredientes;
        this.modoPreparo = modoPreparo;
        this.tempoPreparo = tempoPreparo;
        this.nivelDificuldade = nivelDificuldade;
        this.quantidadePorcoes = quantidadePorcoes;
        this.caminhoFoto = caminhoFoto;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }

    public String getModoPreparo() {
        return modoPreparo;
    }

    public void setModoPreparo(String modoPreparo) {
        this.modoPreparo = modoPreparo;
    }

    public Integer getTempoPreparo() {
        return tempoPreparo;
    }

    public void setTempoPreparo(Integer tempoPreparo) {
        this.tempoPreparo = tempoPreparo;
    }

    public Float getNivelDificuldade() {
        return nivelDificuldade;
    }

    public void setNivelDificuldade(Float nivelDificuldade) {
        this.nivelDificuldade = nivelDificuldade;
    }

    public Integer getQuantidadePorcoes() {
        return quantidadePorcoes;
    }

    public void setQuantidadePorcoes(Integer quantidadePorcoes) {
        this.quantidadePorcoes = quantidadePorcoes;
    }

    public String getCaminhoFoto() {
        return caminhoFoto;
    }

    public void setCaminhoFoto(String caminhoFoto) {
        this.caminhoFoto = caminhoFoto;
    }

    @Override
    public String toString() {
        return getNome() +"\n"+ getNivelDificuldade();
    }
}
