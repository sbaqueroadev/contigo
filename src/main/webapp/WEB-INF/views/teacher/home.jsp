<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Profesor</title>
<link rel="stylesheet"
	href="../webjars/bootstrap/3.3.7-1/css/bootstrap.css">
</head>
<body class="row" ng-app="teacherApp">
	<c:if test="${not empty teacher}">
		<div class="col-md-12" ng-controller="mainInfoCtrlr"
			ng-init='teacherS=${teacher}; subjects=${subjects}'>

			<div class="col-md-12">
				<h3>Hola {{teacherS.name}}</h3>
			</div>
			<div class="col-md-6">
				<h4>Tus áreas</h4>
				<table class="col-md-12">
					<thead>
						<tr>
							<th>Tema
					</thead>
					<tbody>
						<tr ng-repeat="subject in teacherS.subjects">
							<td>{{subject.name}}
							<td><button class="btn btn-success" value="A&ntilde;adir"
									ng-click="removeSubject(subject)">Eliminar</button>
					</tbody>
				</table>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label for="subjects"> Nueva área: </label> <select
						class="form-control" ng-model="subject" id="subjects"
						ng-options="s.name for s in subjects"></select>
				</div>
				<button class="btn btn-success" value="A&ntilde;adir"
					ng-click="addSubject()">A&ntilde;adir</button>
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
						<tr ng-repeat="cClass in teacherS.classes | filter: {status: 'active'}">
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
							ng-repeat="cClass in teacherS.classes | filter: {status: 'asked'}">
							<td>{{cClass.date | date:format:"dd-MM-yyyy" }}
							<td>{{cClass.subject.name}}
							<td><button class="btn btn-success" value="Aceptar"
									ng-click="acceptClass(cClass)">Aceptar</button>
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

	<script src="../resources/js/teacher/home.js" type="text/javascript"></script>
</body>
</html>