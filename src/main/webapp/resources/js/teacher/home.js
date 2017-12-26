/**
 * Teacher Home Angular App
 */
var teacherApp = angular.module("teacherApp", []);

/************************ TEACHER CONTROLLER****************************************/
teacherApp.controller("mainInfoCtrlr", ['$scope','$q','$http','$window',function($scope,$q,$http, $window) {
	
	/************************ ADD SUBJECT PROCESS****************************************/
	$scope.addSubject = function(){
		addSubjectToTeacher().then(
				function( result ){
					showResult(result);
				});
	};
	/********************************************************************************/
	/************************ ADD SUBJECT TO TEACHER****************************************/
	function addSubjectToTeacher(){
		var deferred = $q.defer();
		var data = $scope.subject;
		if(data!=null){
			$http.post("./subject/add",JSON.stringify(data))
			.then(function complete(response){
				if(response.data.type=="OK"){
					$scope.teacherS.subjects.push($scope.subject);
					$scope.subject={};
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
	$scope.removeSubject = function(subject){
		removeSubjectToTeacher(subject).then(
				function( result ){
					showResult(result);
				});
	};
	/********************************************************************************/
	/************************ REMOVE SUBJECT TO TEACHER****************************************/
	function removeSubjectToTeacher(subject){
		var deferred = $q.defer();
		var data = subject;
		if(data!=null){
			$http.post("./subject/remove",JSON.stringify(data))
			.then(function complete(response){
				if(response.data.type=="OK"){
					$scope.teacherS.subjects.splice($scope.teacherS.subjects.indexOf(subject),1);
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
	$scope.acceptClass = function(cClass){
		teacherAcceptsClass(cClass).then(
				function( result ){
					showResult(result);
				});
	};
	/********************************************************************************/
	/************************ TEACHER ACCEPT CLASS****************************************/
	function teacherAcceptsClass(cClass){
		var deferred = $q.defer();
		var data = cClass;
		if(data!=null){
			$http.post("../class/accept/"+data.id)
			.then(function complete(response){
				if(response.data.type=="OK"){
					$scope.teacherS.classes[$scope.teacherS.classes.indexOf(cClass)].status='active';
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


