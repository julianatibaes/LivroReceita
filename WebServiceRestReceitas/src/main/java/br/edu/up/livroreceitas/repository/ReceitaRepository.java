package br.edu.up.livroreceitas.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.edu.up.livroreceitas.repository.entity.ReceitaEntity;


public class ReceitaRepository {
	
	private final EntityManagerFactory entityManagerFactory;
	 
	private final EntityManager entityManager;
	
	public ReceitaRepository(){
		 
		/*CRIANDO O NOSSO EntityManagerFactory COM AS PORPRIEDADOS DO ARQUIVO persistence.xml */
		this.entityManagerFactory = Persistence.createEntityManagerFactory("persistence_unit_db_receita");
		this.entityManager = this.entityManagerFactory.createEntityManager();
	}

	/**
	 * CRIA UM NOVO REGISTRO NO BANCO DE DADOS
	 * */
	public void Salvar(ReceitaEntity receitaEntity){
 
		this.entityManager.getTransaction().begin();
		this.entityManager.persist(receitaEntity);
		this.entityManager.getTransaction().commit();
	}
 
	/**
	 * ALTERA UM REGISTRO CADASTRADO
	 * */
	public void Alterar(ReceitaEntity receitaEntity){
 
		this.entityManager.getTransaction().begin();
		this.entityManager.merge(receitaEntity);
		this.entityManager.getTransaction().commit();
	}
 
	/**
	 * RETORNA TODAS AS RECEITAS CADASTRADAS NO BANCO DE DADOS 
	 * */
	@SuppressWarnings("unchecked")
	public List<ReceitaEntity> TodasReceitas(){
 
		return this.entityManager.createQuery("SELECT r FROM ReceitaEntity r ORDER BY r.nome").getResultList();
	}
 
	/**
	 * CONSULTA UMA RECEITA CADASTRA PELO CÓDIGO
	 * */
	public ReceitaEntity GetReceita(Integer id){
 
		return this.entityManager.find(ReceitaEntity.class, id);
	}
 
	/**
	 * EXCLUINDO UM REGISTRO PELO CÓDIGO
	**/
	public void Excluir(Integer id){
 
		ReceitaEntity receita = this.GetReceita(id);
 
		this.entityManager.getTransaction().begin();
		this.entityManager.remove(receita);
		this.entityManager.getTransaction().commit();
 
	}

}
