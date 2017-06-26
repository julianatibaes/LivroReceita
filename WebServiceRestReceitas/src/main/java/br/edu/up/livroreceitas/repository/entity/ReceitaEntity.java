package br.edu.up.livroreceitas.repository.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_receita")
public class ReceitaEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
 
	@Column(name="nome")	
	private String nome;
 
	@Column(name="nivel_dificuldade")
	private Float nivelDificuldade;

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

	public Float getNivelDificuldade() {
		return nivelDificuldade;
	}

	public void setNivelDificuldade(Float nivelDificuldade) {
		this.nivelDificuldade = nivelDificuldade;
	}

}
