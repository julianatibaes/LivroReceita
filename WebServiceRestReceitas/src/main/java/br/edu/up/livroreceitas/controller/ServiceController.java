package br.edu.up.livroreceitas.controller;

import java.util.ArrayList;
import java.util.List;
 
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import br.edu.up.livroreceitas.http.Receita;
import br.edu.up.livroreceitas.repository.ReceitaRepository;
import br.edu.up.livroreceitas.repository.entity.ReceitaEntity;

/**
 * Essa classe vai expor os nossos métodos para serem acessasdos via http
 * 
 * @Path - Caminho para a chamada da classe que vai representar o nosso serviço
 * */
@Path("/service")
public class ServiceController {
	
	private final  ReceitaRepository repository = new ReceitaRepository();
	 
	/**
	 * @Consumes - determina o formato dos dados que vamos postar
	 * @Produces - determina o formato dos dados que vamos retornar
	 * 
	 * Esse método cadastra uma nova receita
	 * */
	@POST	
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	@Path("/cadastrar")
	public String Cadastrar(Receita receita){
 
		ReceitaEntity entity = new ReceitaEntity();
 
		try {
 
			entity.setNome(receita.getNome());
			entity.setNivelDificuldade(receita.getNivelDificuldade());
 
			repository.Salvar(entity);
 
			return "Registro cadastrado com sucesso!";
 
		} catch (Exception e) {
 
			return "Erro ao cadastrar um registro " + e.getMessage();
		}
 
	}
	
	@POST	
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	@Path("/cadastrarVarios")
	public String CadastrarVarios(List<Receita> receitaList){
 
		ReceitaEntity entity = new ReceitaEntity();
 
		try {
			for(Receita receita: receitaList){
				
				entity.setNome(receita.getNome());
				entity.setNivelDificuldade(receita.getNivelDificuldade());
	 
				repository.Salvar(entity);
			}
			 
			return "Registro cadastrado com sucesso!";
 
		} catch (Exception e) {
 
			return "Erro ao cadastrar um registro " + e.getMessage();
		}
 
	}
 
	/**
	 * Essse método altera uma receita já cadastrada
	 * **/
	@POST
	@Produces("application/json; charset=UTF-8")
	@Consumes("application/json; charset=UTF-8")	
	@Path("/alterar")
	public String Alterar(Receita receita){
 
		ReceitaEntity entity = new ReceitaEntity();
 
		try {
 
			entity.setId(receita.getId());
			entity.setNome(receita.getNome());
			entity.setNivelDificuldade(receita.getNivelDificuldade());
 
			repository.Alterar(entity);
 
			return "Registro alterado com sucesso!";
 
		} catch (Exception e) {
 
			return "Erro ao alterar o registro " + e.getMessage();
 
		}
 
	}
	/**
	 * Esse método lista todas receitas cadastradas na base
	 * */
	@GET
	@Produces("application/json; charset=UTF-8")
	@Path("/todasReceitas")
	public List<Receita> TodasReceitas(){
 
		List<Receita> receitas =  new ArrayList<Receita>();
 
		List<ReceitaEntity> listaEntityReceitas = repository.TodasReceitas();
 
		for (ReceitaEntity entity : listaEntityReceitas) {
 
			receitas.add(new Receita(entity.getId(), entity.getNome(),entity.getNivelDificuldade()));
		}
 
		return receitas;
	}
 
	/**
	 * Esse método busca uma receita cadastrada pelo código
	 * */
	@GET
	@Produces("application/json; charset=UTF-8")
	@Path("/getReceita/{id}")
	public Receita GetReceita(@PathParam("id") Integer id){
 
		ReceitaEntity entity = repository.GetReceita(id);
 
		if(entity != null)
			return new Receita(entity.getId(), entity.getNome(),entity.getNivelDificuldade());
 
		return null;
	}
 
	/**
	 * Excluindo uma receita pelo código
	 * */
	@DELETE
	@Produces("application/json; charset=UTF-8")
	@Path("/excluir/{id}")	
	public String Excluir(@PathParam("id") Integer id){
 
		try {
			repository.Excluir(id);
			return "Registro excluido com sucesso!";
 
		} catch (Exception e) {
 
			return "Erro ao excluir o registro! " + e.getMessage();
		}
 
	}

}
