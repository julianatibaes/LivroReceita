package livroreceitas.tibaes.com.livroreceitas.model;

import java.io.Serializable;

/**
 * Created by Juliana Tibães on 05/06/2017.
 * LivroReceitas
 */

public class Receita implements Serializable {

    private Integer id;
    private String nome;
    private String ingredientes;
    private String modoPreparo;
    private Integer tempoPreparo;
    private Float nivelDificuldade;
    private Integer quantidadePorcoes;

    public Receita() {
    }

    public Receita(String nome, Float nivelDificuldade) {
        this.nome = nome;
        this.nivelDificuldade = nivelDificuldade;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    @Override
    public String toString() {
        return getNome() +"\n "+ getNivelDificuldade();
    }
}