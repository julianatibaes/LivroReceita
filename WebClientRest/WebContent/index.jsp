<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<!-- ng-app nos diz qual módulo estamos trabalhando -->
<html  ng-app="livroreceitas">

<head>
	<meta name="viewport" content="width=device-width">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

	<!-- Colocando as bibliotecas do bootstrap, para deixar
	meu site mais bonito -->
	<link rel="stylesheet" href="css/bootstrap.min.css">
	<link rel="stylesheet" href="css/bootstrap-theme.min.css">
	
	<!-- Colocando as bibliotecas do AngularJS -->
	<script src="js/lib/angular.min.js"></script>
	<script src="js/lib/angular-route.min.js"></script>
	
	<!-- Chamando a classe principal do nosso AngularJS
	Nela quem definimos o módulo que iremos trabalhar no
	projeto -->
	<script src="main.js"></script>
	
	<script src="js/controller/receitas-controller.js"></script>

<title>Livro de receitas</title>
</head>

<!-- ng-controller seta o controller que definimos dentro do receitas-controller  -->
<body ng-controller="ReceitasController">

	<!-- DIV principal da minha tela, usando bootstrap -->
	<div class="container">
	
	<!-- DIV para colocar o título da página, usando bootstrap -->
	<div class="jumbotron">
		<h1 class="text-center"> Livro de Receitas </h1>
	</div>
	
	<!-- a row com o campo de busca -->
    <div class="row">
     	<!-- agrupar os dados que serão inseridos, no caso a busca -->
	    <div class="input-group col-md-12 col-xs-12">
       
	        <form>
	  			<!-- input para fazer a busca do item da lista-->     
	  			<!-- ng-model seta o modelo que utilizarei, no caso 
	  			o filtro --> 
	             <input class="form-control" 
	             placeholder="filtrar pelo nome da receita" 
	             ng-model="filtro">
	        </form>
        </div> <!-- fim input-group -->
    </div> <!-- fim row -->
	
	<div class="row">
		<!-- Crio uma tabela que utiliza as classes do bootstrap
		ela será: mesclada e responsiva -->
		<table class="table table-striped table-responsive">
		
			<!-- Definir a primeira linha da minha tabela -->
			<thead>
			   <tr>
			   	<th>ID</th>
				<th>Nome</th>
				<th>Nível Dificuldade</th>
				<th>Editar</th>
				<th>Excluir</th>
			   </tr>
			</thead>
			
			<!-- define o corpo da tabela -->	
			<tbody>
				<!-- ng-repeat permite a repetição da estrutura
				para cada item de retorno da lista -->
				<tr ng-repeat = "receita in listaReceitas | filter: filtro ">
					<td>{{receita.id}}</td>
					<td>{{receita.nome}}</td>
					<td>{{receita.nivelDificuldade}}</td>
					<td>
						<a class="btn btn-primary btn-block" href="http://localhost:8080/WebClientRest/receita-nova.html" >Editar</a>
					</td>
					<td><button  class="btn btn-danger btn-block" ng-click="remover(receita)">
							Remover
						</button>
					</td>
				</tr>
			</tbody>
		</table> <!-- termina tabela -->
	</div> <!-- termina div da tabela -->
	<div class="row">
		<a class="btn btn-primary btn-block" href="http://localhost:8080/WebClientRest/receita-nova.html" >Nova receita</a>
	</div>
		
	</div> <!-- termina div do body (container) -->
	
</body>
</html>