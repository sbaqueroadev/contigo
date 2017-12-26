<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="../webjars/bootstrap/3.3.7-1/css/bootstrap.css">
</head>
<body class="row" ng-app="studentApp">
	<c:if test="${not empty student}">
		<div class="col-md-12" ng-controller="mainInfoCtrlr"
			ng-init='studentS=${student}; subjects=${subjects}'>

			<div class="col-md-12">
				<h3>Hola {{studentS.name}}</h3>
			</div>
			<div class="col-md-6">
				<h4>Solicitar nueva clase</h4>

				<div class="form-group">
					<label for="subjects"> Área: </label> <select class="form-control"
						ng-model="selectedClass.subject" id="subjects"
						ng-options="s.name for s in subjects"></select>
				</div>
				<div class="form-group">
					<label for="topic"> Tema: </label> <input type="text"
						class="form-control" ng-model="selectedClass.topic" id="topic" />
				</div>
				<div class="form-group">
					<label for="date"> Fecha de clase: </label> <input type="date"
						class="form-control" ng-model="selectedClass.date" id="date" />
				</div>
				<button class="btn btn-success" value="A&ntilde;adir"
					ng-click="lookForClass()">Solicitar</button>
			</div>

			<div class="col-md-6">
				<h4>Clases próximas</h4>
				<table class="col-md-12">
					<thead>
						<tr>
							<th>Fecha
							<th>Tema
					</thead>
					<tbody>
						<tr
							ng-repeat="cClass in studentS.classes | filter: {status: 'active'}">
							<td>{{cClass.date | date:format:"dd-MM-yyyy" }}
							<td>{{cClass.subject.name}}
					</tbody>
				</table>
			</div>
			<div class="col-md-6">
				<h4>Clases Pedidas</h4>
				<table class="col-md-12">
					<thead>
						<tr>
							<th>Fecha
							<th>Tema
					</thead>
					<tbody>
						<tr
							ng-repeat="cClass in studentS.classes | filter: {status: 'asked'}">
							<td>{{cClass.date | date:format:"dd-MM-yyyy" }}
							<td>{{cClass.subject.name}}
					</tbody>
				</table>
			</div>
		</div>
	</c:if>
	<c:if test="${not empty currentClass}">
		<p style="padding-top: 20px;" class="col-md-12">
			<br> <br> <br> <a class="col-md-12"
				href="../board/home/${currentClass.id}">Ir a clase</a>
		</p>
	</c:if>
	<script type="text/javascript" src="../webjars/jquery/3.2.1/jquery.js"></script>
	<script type="text/javascript"
		src="../webjars/angular/1.6.7-1/angular.js"></script>

	<script src="../resources/js/student/home.js" type="text/javascript"></script>
</body>
</html>