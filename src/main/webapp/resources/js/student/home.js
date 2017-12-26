/**
 * Student Home Angular App
 */
var studentApp = angular.module("studentApp", []);

/************************ TEACHER CONTROLLER****************************************/
studentApp.controller("mainInfoCtrlr", ['$scope','$q','$http','$window',function($scope,$q,$http, $window) {
	
	/************************ ADD SUBJECT PROCESS****************************************/
	$scope.lookForClass = function(){
		lookForClass().then(
				function( result ){
					showResult(result);
				});
	};
	/********************************************************************************/
	/************************ ADD SUBJECT TO TEACHER****************************************/
	function lookForClass(){
		var deferred = $q.defer();
		var data = $scope.selectedClass;
		if(data!=null){
			$http.post("../class/available/list",JSON.stringify(data))
			.then(function complete(response){
				if(response.data.type=="OK"){
					$scope.availableClasses.push(response.data.data);
					deferred.resolve(response.data);
				}else{
					deferred.resolve(response.data);
				}
			},
			function error(response){
				deferred.resolve({type:"error"});
			});
		}else{
			deferred.resolve({type:"error"});
		}
		return deferred.promise;
	}
	/********************************************************************************/
	/************************ REMOVE SUBJECT PROCESS****************************************/
	/*$scope.removeSubject = function(subject){
		removeSubjectToTeacher(subject).then(
				function( result ){
					showResult(result);
				});
	};
	/********************************************************************************/
	/************************ REMOVE SUBJECT TO TEACHER****************************************/
	/*function removeSubjectToTeacher(subject){
		var deferred = $q.defer();
		var data = subject;
		if(data!=null){
			$http.post("./subject/remove",JSON.stringify(data))
			.then(function complete(response){
				if(response.data.type=="OK"){
					$scope.studentS.subjects.splice($scope.studentS.subjects.indexOf(subject),1);
					deferred.resolve(response.data);
				}else{
					deferred.resolve(response.data);
				}
			},
			function error(response){
				deferred.resolve({type:"error"});
			});
		}else{
			deferred.resolve({type:"error"});
		}
		return deferred.promise;
	}
	/********************************************************************************/
	
	/********************************************************************************/
	/************************ ACCPET CLASS PROCESS****************************************/
	/*$scope.acceptClass = function(cClass){
		studentAcceptsClass(cClass).then(
				function( result ){
					showResult(result);
				});
	};
	/********************************************************************************/
	/************************ TEACHER ACCEPT CLASS****************************************/
	/*function studentAcceptsClass(cClass){
		var deferred = $q.defer();
		var data = cClass;
		if(data!=null){
			$http.post("../class/accept/"+data.id)
			.then(function complete(response){
				if(response.data.type=="OK"){
					$scope.studentS.classes[$scope.studentS.classes.indexOf(cClass)].status='active';
					deferred.resolve(response.data);
				}else{
					deferred.resolve(response.data);
				}
			},
			function error(response){
				deferred.resolve({type:"error"});
			});
		}else{
			deferred.resolve({type:"error"});
		}
		return deferred.promise;
	}
	/********************************************************************************/
	
	function showResult(result){
		if(result.type){
			alert(result.msg);
		}
	}
	/********************************************************************************/
}]);
/********************************************************************************/


