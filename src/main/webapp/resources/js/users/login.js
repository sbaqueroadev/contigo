/**
 * Asset adding Angular App
 */
var loginApp = angular.module("loginApp", []);

/************************ LIST CONTROLLER****************************************/
loginApp.controller("loginController", ['$scope','$q','$http','$window',function($scope,$q,$http, $window) {
	$scope.asset = {};
	/************************ SEND DATA PROCESS****************************************/
	$scope.login = function(e){
		e.preventDefault();
		loginFinish().then(function(result){});
		return false;
	};
	/********************************************************************************/
	/************************ SEND NEW ASSET****************************************/
	function loginFinish(){
		var deferred = $q.defer();
		var data = $scope.user;
		$http.post("../login",JSON.stringify(data))
		.then(function success(response){
			/*if(response.data.result=="OK"){
				$scope.asset={};
				$("#addAssetForm").trigger("reset");*/
				alert("Login correctly!");
				$window.location.href = '../board/home';
			/*}else
				alert("Error. Try later please.");*/
			deferred.resolve(response.data);
		},
		function error(response){
			if(response.status=403){
				$window.location.href = '../users/access';
				//$location.path('../users/access');
			}
			deferred.resolve();//[{id:1,name:"P1"}]);
		});
		return deferred.promise;
	}
	/********************************************************************************/
}]);
/********************************************************************************/
