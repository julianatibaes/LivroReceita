angular.module('livroreceitas', ['ngRoute'])
.config(function($routeProvider, $locationProvider) {

	$locationProvider.html5Mode(true);

	$routeProvider.when('/WebClientRest/receitas', {
		templateUrl: 'http://localhost:8080/WebClientRest/index.jsp'

	});

	$routeProvider.when('/receita/new', {
		templateUrl: 'http://localhost:8080/WebClientRest/nova-receita.jsp'

	});

	$routeProvider.when('/WebClientRest/receita/edit/:receitaId', {
		templateUrl: 'http://localhost:8080/WebClientRest/nova-receita.jsp'

	});

	$routeProvider.otherwise({redirectTo: '/WebClientRest/receitas'});

});