angular.module('livroreceitas').
controller('ReceitasController', function($scope, $http){
	
	//vou utilizar para colocar o que receber do $http
	$scope.listaReceitas = [];
	
	//vou utilizar para filtrar os dados por ele
	$scope.filtro='';
	
	//busca no link utilizando o método get. Caso consiga acessar,
	// irá criar uma função recebedo o retorno e jogando o retorno
	// no array da listaReceitas. Se algo de errado ocorrer, mostrará
	// o erro no console
	$http.get('http://localhost:8080/WebServiceRest/rest/service/'+
			'todasReceitas').
    success(function(retorno) {
        $scope.listaReceitas = retorno;
    }).error(function(erro) {
        console.log(erro);
    });
	
	
	$scope.remover = function(receita) {
		console.log('Receita' + receita.nome);
		$http.delete('http://localhost:8080/WebServiceRest/rest/service/excluir/' + receita.id)
        .success(function() {
            console.log('Receita' + receita.nome+ ' removida com sucesso!');
            var indice = $scope.listaReceitas.indexOf(receita); // obtem o índice da foto que está sendo removida
            $scope.listaReceitas.splice(indice, 1); // remove um elemento da lista de fotos de acordo com o índice da foto removida.

        })
        .error(function(erro) {
            console.log('Não foi possível apagar a receita ' + foto.nome);
        });
    };
});

