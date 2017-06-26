package com.tibaes.livroreceitaretrofit.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;

/**
 * Created by Juliana Tibães on 25/06/2017.
 * LivroReceitaRetrofit
 */
@JsonPropertyOrder({
        "id",
        "nivelDificuldade",
        "nome"
})
public class Receita implements Serializable {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("nome")
    private String nome;
  //  private String ingredientes;
  //  private String modoPreparo;
  //  private Integer tempoPreparo;

    @JsonProperty("nivelDificuldade")
    private Float nivelDificuldade;
  //  private Integer quantidadePorcoes;
  //  private String caminhoFoto;

    public Receita() {
    }

    public Receita(String nome, Float nivelDificuldade) {
        this.nome = nome;
        this.nivelDificuldade = nivelDificuldade;
    }

    public Receita(Long id, String nome, Float nivelDificuldade) {
        this.id = id;
        this.nome = nome;
        this.nivelDificuldade = nivelDificuldade;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public Float getNivelDificuldade() {
        return nivelDificuldade;
    }

    public void setNivelDificuldade(Float nivelDificuldade) {
        this.nivelDificuldade = nivelDificuldade;
    }

    @Override
    public String toString() {
        return getNome() +"\n"+ getNivelDificuldade();
    }

}
