angular.module('livroreceitas').
controller('ReceitaNovaController', function($scope, $http){
	$scope.receita = {};
	
	// funçao que será chamada no ng-submit do formulário de cadastro
	$scope.submeter = function() {
		
		// define os headers para a hora de enviar dos dados
		$http.defaults.headers.post['Content-Type'] = 'application/json; charset=UTF-8';
		
		$http.post(
				 'http://localhost:8080/WebServiceRest/rest/service/cadastrar', 
				 $scope.receita
				 )
         .success(function() {
             console.log('Receita adicionada com sucesso');
         })
         .error(function(erro) {
             console.log('Não foi possível cadastrar a receita');
         });
	 }
	 
});