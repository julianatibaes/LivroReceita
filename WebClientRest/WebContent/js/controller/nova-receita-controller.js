angular.module('livroreceitas')
.controller('NovaReceitaController', function($scope, $http, $routeParams) {
	 $scope.receita = {};
	 
	 if($routeParams.receitaId) {
         // busca a foto no servidor
		 $http.get('http://localhost:8080/WebServiceRest/rest/service/getReceita/' + $routeParams.receitaId)
         .success(function(receita) {
             $scope.receita = receita;
         })
         .error(function(erro) {
             console.log(erro);
             $scope.mensagem = 'Não foi possível obter a receita'
         });
     }
	 
	 $scope.submeter = function() {
		 
		  if($routeParams.receitaId) {

              $http.put('http://localhost:8080/WebServiceRest/rest/service/alterar' + 
            		  $scope.receita.id, $scope.receita)
              .success(function() {
                  $scope.mensagem = 'Receita ' + $scope.receita.nome + ' foi alterada';

              })
              .error(function(erro) {
                  console.log(erro);
                  console.log('Não foi possível alterar a receita');
              });

          } else { 
        	 $http.defaults.headers.post['Content-Type'] = 'application/json';
   		   
     		 $http.post(
     				 'http://localhost:8080/WebServiceRest/rest/service/cadastrar', 
     				 $scope.receita
     				 )
              .success(function() {
                  console.log('Receita adicionada com sucesso');
              })
              .error(function(erro) {
                  console.log('Não foi possível cadastrar a receita');
              })
          }
		 
     };
         
	 
		
});



