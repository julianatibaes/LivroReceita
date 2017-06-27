<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="livroreceitas">
	<head>
		<meta name="viewport" content="width=device-width">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Cliente Livro de Receitas</title>
		
		<link rel="stylesheet" href="css/bootstrap.min.css">
		<link rel="stylesheet" href="css/bootstrap-theme.min.css">
		
		<script src="js/lib/angular.min.js"></script>
        <script src="js/lib/angular-animate.min.js"></script>
        <script src="js/lib/angular-route.min.js"></script>
		
		<!-- <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.2.32/angular.min.js"></script> -->
		<script src="js/main.js"></script>
		
		<script src="js/controller/nova-receita-controller.js"></script>
		
	</head>
	<body ng-controller=NovaReceitaController>
		<div class="container">
			<div class="jumbotron">
				<h1 class="text-center"> Receitas </h1>
			</div>
		
			<!-- a row com o campo de busca -->
            <div class="row">
            <div class="input-group">
			
              <!--  <p ng-show="mensagem.length" class="alert alert-info">{{mensagem}}</p> -->
                
                <form name="formulario" class="row" ng-submit="submeter()">
                 <div class="col-md-6 col-xs-12">
                 
			        <div class="form-group">
			            <label>Nome Receita</label>
			            
			            <input name="nome" class="form-control" 
                			ng-model="receita.nome" required
                			ng-maxlength="50"/>
                		
                		<!-- 
                		<span ng-show = "formulario.$submitted && formulario.nome.$error.required" 
                			class="form-control alert-danger">
                			Nome obrigatório
            			</span> 
            			<span ng-show="formulario.$submitted && formulario.nome.$error.maxlength" class="form-control alert-danger">
                			No máximo 50 caracteres!
            			</span>  
                		 --> 
            			
        			</div>
        			
        			<div class="form-group">
			            <label>Nível de Dificuldade</label>
			            
			            <input name="nivelDificuldade" class="form-control" 
                			ng-model="receita.nivelDificuldade"/>
        			</div>
        			<hr>
        			<button type="submit" class="btn btn-primary" ng-disabled="formulario.$invalid">
            			Salvar
        			</button>
         			<a href="http://localhost:8080/WebClientRest/" class="btn btn-primary">Voltar</a>
        			<hr>
                </div>	
			   </form>
		    </div>
		    </div>
		</div>
	</body>
</html>