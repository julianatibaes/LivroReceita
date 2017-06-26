package br.edu.up.livroreceitas.http;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Receita {
	
	private int id;
	private String nome;
	private float nivelDificuldade;
	
	public Receita() {	}

	public Receita(int id, String nome, float nivelDificuldade) {
		this.id = id;
		this.nome = nome;
		this.nivelDificuldade = nivelDificuldade;
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

	public float getNivelDificuldade() {
		return nivelDificuldade;
	}

	public void setNivelDificuldade(float nivelDificuldade) {
		this.nivelDificuldade = nivelDificuldade;
	}

}
